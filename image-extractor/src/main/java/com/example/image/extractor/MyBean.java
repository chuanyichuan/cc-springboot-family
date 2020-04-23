package com.example.image.extractor;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class MyBean implements FactoryBean<MyBean> {

    private String message;

    public MyBean() {
        System.out.println("通过Constructor获取bean...");
        this.message = "通过Constructor获取bean...";
    }

    public MyBean(String message) {
        this.message = message;
    }

    @Override
    public MyBean getObject() throws Exception {
        System.out.println("通过getObject获取bean...");
        return new MyBean("通过getObject获取bean...");
    }

    @Override
    public Class<?> getObjectType() {
        return this.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
