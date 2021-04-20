package cc.kevinlu.nacos.consumer;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "order")
public interface OrderFeign {

    boolean order(String userId, String commodityId, int count, int m);

}
