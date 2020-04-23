package cc.kevinlu.ccspringbootstarterautoconfigurer.wt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({ CcService.class })
@EnableConfigurationProperties({ CcProperties.class })
public class CcAutoConfiguration {

    @Autowired
    private CcProperties ccProperties;

    @Bean
    @ConditionalOnMissingBean({ CcService.class })
    public CcService ccService() {
        CcService ccService = new CcService();
        System.out.println("------auto register!------");
        ccService.setCcProperties(ccProperties);
        return ccService;
    }

}
