package cc.kevinlu.cc.seata.service;

public interface OrderService {

    String order(String userId, String commodityCode, int count);

}
