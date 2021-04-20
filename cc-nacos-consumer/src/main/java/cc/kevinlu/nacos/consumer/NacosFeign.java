package cc.kevinlu.nacos.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "client1")
public interface NacosFeign {

    @RequestMapping(value = "/nacos/info")
    String info();

}
