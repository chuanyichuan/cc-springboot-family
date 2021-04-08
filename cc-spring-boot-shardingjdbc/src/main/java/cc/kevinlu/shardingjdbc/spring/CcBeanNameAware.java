package cc.kevinlu.shardingjdbc.spring;

import org.springframework.beans.factory.BeanNameAware;

public class CcBeanNameAware implements BeanNameAware {

    @Override
    public void setBeanName(String s) {
        System.out.println("[bean name] : " + s);
    }
}
