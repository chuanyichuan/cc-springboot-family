package cc.kevinlu.cc.seata.controller;

import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.cc.seata.client.UserClient;

@RestController
public class UserClientController implements UserClient {
    @Override
    public void saveUserAndBug() {
    }
}
