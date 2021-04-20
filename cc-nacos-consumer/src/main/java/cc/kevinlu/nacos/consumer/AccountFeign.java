package cc.kevinlu.nacos.consumer;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "account")
public interface AccountFeign {

    boolean money(String userId, int m);

}
