package cc.kevinlu.springcloudcuserservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable(name = "id") Integer id) {
        System.out.println("id = " + id);
        return "name = " + id;
    }

}
