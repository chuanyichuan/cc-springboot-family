package cc.kevinlu.springbootldap.bean;

import java.util.List;

public class Person {

    //用户名
    private String       name;
    //登录名
    private String       sAMAccountName;
    //所属组列表
    private List<String> role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsAMAccountName() {
        return sAMAccountName;
    }

    public void setsAMAccountName(String sAMAccountName) {
        this.sAMAccountName = sAMAccountName;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    @Override
    public String toString() {

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("name:" + name + ",");
        stringBuffer.append("sAMAccountName:" + sAMAccountName);
        if (role != null && role.size() > 0) {
            stringBuffer.append(",role:" + String.join(",", role));
        }

        return stringBuffer.toString();
    }

}
