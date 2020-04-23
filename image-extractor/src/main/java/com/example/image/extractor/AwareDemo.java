package com.example.image.extractor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

@Component
public class AwareDemo implements BeanNameAware, InitializingBean, ApplicationContextAware, BeanFactoryAware,
        EnvironmentAware, ImportAware {

    private BeanFactory beanFactory;
    private String beanName;
    private ApplicationContext context;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this.beanName + " 初始化完成");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("初始化环境: " + JSONObject.toJSONString(environment));
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        System.out.println("annotation: " + importMetadata.hasAnnotatedMethods("Override"));
    }
}
