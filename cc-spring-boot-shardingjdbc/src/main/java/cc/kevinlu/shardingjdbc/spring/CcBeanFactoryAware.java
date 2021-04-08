package cc.kevinlu.shardingjdbc.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import cc.kevinlu.shardingjdbc.service.UserInfoService;

@Configuration
public class CcBeanFactoryAware implements BeanFactoryAware, ApplicationContextAware {

    private BeanFactory        beanFactory;

    private ApplicationContext applicationContext;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        pt();
    }

    public void pt() {
        UserInfoService clazz = this.beanFactory.getBean(UserInfoService.class);
        ClassLoader loader = clazz.getClass().getClassLoader();
        try {
            loader.loadClass("cc.kevinlu.shardingjdbc.spring.CcBeanNameAware");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
