package cc.kevinlu.cc.seata.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "seata")
@RequestMapping("/user")
public interface UserClient {

    void saveUserAndBug();

}
