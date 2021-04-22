package cc.kevinlu.account;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cc.kevinlu.account.service.AccountService;

@RequestMapping("/account")
@RestController
public class AccountController {

    @Resource
    private AccountService accountService;

    @GetMapping("/money")
    public boolean money(@RequestParam String userId, @RequestParam int m) {
        return accountService.money(userId, m);
    }

}
