package cc.kevinlu.sbjwt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 需要登录后才能访问
 */
@Slf4j
@RestController
public class SecureController {

    /**
     * 查询 用户信息，登录后才能访问
     */
    @RequestMapping("/secure/getUserInfo")
    public String login(HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute("id");
        String name = request.getAttribute("name").toString();
        String userName = request.getAttribute("userName").toString();
        return "当前用户信息id=" + id + ",name=" + name + ",userName=" + userName;
    }
}
