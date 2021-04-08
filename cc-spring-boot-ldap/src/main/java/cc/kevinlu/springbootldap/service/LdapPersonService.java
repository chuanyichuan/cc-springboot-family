package cc.kevinlu.springbootldap.service;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cc.kevinlu.springbootldap.bean.Person;

@Service
public class LdapPersonService {

    @Autowired
    private LdapTemplate ldapTemplate;

    @Value("${spring.ldap.domainName}")
    private String       ldapDomainName;

    public Person findByUsername(String username, String password) throws NamingException {
        //这里注意用户名加域名后缀  userDn格式：abcd@rikylee.com
        //        String userDn = username + ldapDomainName;
        //使用用户名、密码验证域用户
        //        DirContext ctx = ldapTemplate.getContextSource().getContext(userDn, password);
        //如果验证成功根据sAMAccountName属性查询用户名和用户所属的组
        Person person = ldapTemplate.search(query().where("objectclass").is("person"), new AttributesMapper<Person>() {
            @Override
            public Person mapFromAttributes(Attributes attributes) throws NamingException {
                Person person = new Person();
                person.setName(attributes.get("cn").get().toString());
                person.setsAMAccountName(attributes.get("description").get().toString());

                String memberOf = attributes.get("telephonenumber").toString().replace("telephoneNumber:", "");
                List<String> list = new ArrayList<>();
                String[] roles = memberOf.split(",");
                for (String role : roles) {
                    if (StringUtils.startsWithIgnoreCase(role.trim(), "CN=")) {
                        list.add(role.trim().replace("CN=", ""));
                    }
                }
                person.setRole(list);

                return person;
            }
        }).get(0);
        //关闭ldap连接
        //        LdapUtils.closeContext(ctx);

        return person;
    }

}
