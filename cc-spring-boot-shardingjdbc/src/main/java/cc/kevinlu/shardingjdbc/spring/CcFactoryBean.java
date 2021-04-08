package cc.kevinlu.shardingjdbc.spring;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CcFactoryBean implements FactoryBean<CcBeanNameAware> {
    @Override
    public CcBeanNameAware getObject() throws Exception {
        System.out.println("before bean init...");
        return new CcBeanNameAware();
    }

    @Override
    public Class<?> getObjectType() {
        return CcBeanNameAware.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
