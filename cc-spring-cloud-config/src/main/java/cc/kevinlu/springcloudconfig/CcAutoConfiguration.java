package cc.kevinlu.springcloudconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CcProperties.class)
@ConditionalOnMissingBean(MysqlConfig.class)
@ConditionalOnMissingClass({ "cc.kevinlu.springcloudconfig.MysqlConfig" })
public class CcAutoConfiguration {

    public CcAutoConfiguration() {
        System.out.println("CcAutoConfiguration init......");
    }
}
