package cc.kevinlu.ccstarterdemo;

import java.io.Serializable;

public class PersonDTO implements Serializable {

    @JSONFilter(condition = "${jn.id} == false")
    private Long    id;

    private String  name;

    private Integer age;

    @JSONFilter(condition = "s == 123")
    private String  gender;

    private Long    s;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getS() {
        return s;
    }

    public void setS(Long s) {
        this.s = s;
    }
}
