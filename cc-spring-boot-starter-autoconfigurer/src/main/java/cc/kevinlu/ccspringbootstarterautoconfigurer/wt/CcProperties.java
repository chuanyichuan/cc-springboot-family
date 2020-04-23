package cc.kevinlu.ccspringbootstarterautoconfigurer.wt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cc.config")
public class CcProperties {

    private String  name = "cc";

    private Integer age  = 3;

    private String  birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "CcProperties{" + "name='" + name + '\'' + ", age=" + age + ", birthday='" + birthday + '\'' + '}';
    }
}
