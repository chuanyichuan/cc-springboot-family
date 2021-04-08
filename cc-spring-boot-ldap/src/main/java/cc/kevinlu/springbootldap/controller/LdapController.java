package cc.kevinlu.springbootldap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.springbootldap.bean.Person;
import cc.kevinlu.springbootldap.service.LdapPersonService;

@RestController
public class LdapController {

    @Autowired
    private LdapPersonService ldapPersonService;

    @GetMapping(value = "t")
    public void findByUsernameTest() {
        try {
            Person person = ldapPersonService.findByUsername("admin", "ldap123");
            System.out.println("person: " + person.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
