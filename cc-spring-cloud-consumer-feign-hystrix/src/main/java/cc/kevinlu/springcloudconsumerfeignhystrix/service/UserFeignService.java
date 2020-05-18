package cc.kevinlu.springcloudconsumerfeignhystrix.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cc.kevinlu.springcloudconsumerfeignhystrix.service.impl.UserFeignFailBackImpl;

//表示"user-service"的服务，指定fallback
@FeignClient(value = "user-service", fallback = UserFeignFailBackImpl.class)
public interface UserFeignService {

    @RequestMapping(value = "/provider/getUser")
    public String getUser(@RequestParam("id") Integer id);
}
