package cc.kevinlu.deferred;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.DebuggingClassWriter;

@SpringBootApplication
public class SpringBootDeferredApplication {

    public static void main(String[] args) {
        // 设置CGLib代理类的生成位置
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "./cgj");
        // 设置JDK代理类的输出
        //        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        SpringApplicationSelf.run(SpringBootDeferredApplication.class, args);
    }

}
