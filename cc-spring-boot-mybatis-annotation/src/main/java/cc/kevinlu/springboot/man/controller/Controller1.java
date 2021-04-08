package cc.kevinlu.springboot.man.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.springboot.man.entity.User;

@RestController
@RequestMapping("/user")
public class Controller1 {

    @Resource
    private UserController userController;

    @GetMapping("/test")
    public String test() {
        User user = new User();
        user.setName("cc");
        user.setPassword("cc");
        user.setSex(1);
        user.setDes("ccc");
        userController.add(user);
        return "success";
    }

}
