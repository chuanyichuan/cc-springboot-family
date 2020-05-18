package cc.kevinlu.springcloudcuserfeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cuser-service")
public interface UserService {

    @GetMapping("/user/{id}")
    String getUser(@PathVariable(name = "id") Integer id);

}
