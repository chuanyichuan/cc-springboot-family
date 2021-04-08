package cc.kevinlu.multi.db.annotation.make;

import java.lang.reflect.Method;
import java.util.Stack;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import cc.kevinlu.multi.db.annotation.MultiTransaction;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chuan
 */
@Slf4j
@Aspect
@Component
public class MultiTransactionAspect implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Pointcut("@annotation(cc.kevinlu.multi.db.annotation.MultiTransaction)")
    public void multiTransactionPointcut() {
        log.info("pointcut start...");
    }

    @Before("multiTransactionPointcut()")
    public void before(JoinPoint joinPoint) {
        log.info("before");
    }

    @Around(value = "multiTransactionPointcut()")
    public Object aroundProceed(ProceedingJoinPoint joinPoint) throws Throwable {
        MultiTransaction multiTransaction = logPrintValue(joinPoint);
        Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack = new Stack<>();
        Stack<TransactionStatus> transactionStatusStack = new Stack<>();
        try {
            // 开启事务
            if (!openTransaction(dataSourceTransactionManagerStack, transactionStatusStack, multiTransaction)) {
                return null;
            }
            Object obj = joinPoint.proceed();
            // 提交事务
            commit(dataSourceTransactionManagerStack, transactionStatusStack);
            return obj;
        } catch (Throwable throwable) {
            log.info("rollback the multi transaction");
            // 回滚事务
            rollback(dataSourceTransactionManagerStack, transactionStatusStack);
            throw throwable;
        }
    }

    private MultiTransaction logPrintValue(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        return method.getAnnotation(MultiTransaction.class);
    }

    /**
     * 开启事务
     * 
     * @param dataSourceTransactionManagerStack 事务名称栈
     * @param transactionStatusStack 事务状态栈
     * @param multiTransaction 事务注解
     * @return true: 事务开启成功, false: 事务开启失败
     */
    private boolean openTransaction(Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack,
                                    Stack<TransactionStatus> transactionStatusStack,
                                    MultiTransaction multiTransaction) {
        String[] values = multiTransaction.values();
        // 判断是否存在指定事务
        if (values == null || values.length == 0) {
            return false;
        }
        // 获取事务并开启事务
        for (String beanName : values) {
            DataSourceTransactionManager dataSourceTransactionManager = (DataSourceTransactionManager) applicationContext
                    .getBean(beanName);
            TransactionStatus transactionStatus = dataSourceTransactionManager
                    .getTransaction(new DefaultTransactionDefinition());

            dataSourceTransactionManagerStack.push(dataSourceTransactionManager);
            transactionStatusStack.push(transactionStatus);
        }
        return true;
    }

    /**
     * 提交事务
     * 
     * @param dataSourceTransactionManagerStack
     * @param transactionStatusStack
     */
    private void commit(Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack,
                        Stack<TransactionStatus> transactionStatusStack) {
        while (!dataSourceTransactionManagerStack.isEmpty()) {
            dataSourceTransactionManagerStack.pop().commit(transactionStatusStack.pop());
        }
    }

    /**
     * 回滚事务
     * 
     * @param dataSourceTransactionManagerStack
     * @param transactionStatusStack
     */
    private void rollback(Stack<DataSourceTransactionManager> dataSourceTransactionManagerStack,
                          Stack<TransactionStatus> transactionStatusStack) {
        while (!dataSourceTransactionManagerStack.isEmpty()) {
            dataSourceTransactionManagerStack.pop().rollback(transactionStatusStack.pop());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
