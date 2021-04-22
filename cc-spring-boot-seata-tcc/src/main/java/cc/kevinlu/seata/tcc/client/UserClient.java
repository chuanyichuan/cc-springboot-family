package cc.kevinlu.seata.tcc.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "seata")
@RequestMapping("/user")
public interface UserClient {

    void saveUserAndBug();

}
