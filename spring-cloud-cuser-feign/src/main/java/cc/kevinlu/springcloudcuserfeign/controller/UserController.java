package cc.kevinlu.springcloudcuserfeign.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.springcloudcuserfeign.service.UserService;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/cuser/{id}")
    public String getUser(@PathVariable(name = "id") Integer id) {
        return userService.getUser(id);
    }

}
