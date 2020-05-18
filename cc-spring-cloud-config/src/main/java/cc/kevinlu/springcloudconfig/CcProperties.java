package cc.kevinlu.springcloudconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@SuppressWarnings("ALL")
@ConfigurationProperties(prefix = "cc.chuan")
public class CcProperties {

    private String name   = "cyc";

    private int    age    = 22;

    private String gender = "男";

}
