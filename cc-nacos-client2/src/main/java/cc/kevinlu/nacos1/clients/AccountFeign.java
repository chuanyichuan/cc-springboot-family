package cc.kevinlu.nacos1.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "account")
public interface AccountFeign {

    @GetMapping("/account/money")
    boolean money(@RequestParam("userId") String userId, @RequestParam("m") int m);

}
