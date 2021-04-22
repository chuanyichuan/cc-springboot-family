package cc.kevinlu.seata.tcc.controller;

import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.seata.tcc.client.UserClient;

@RestController
public class UserClientController implements UserClient {
    @Override
    public void saveUserAndBug() {
    }
}
