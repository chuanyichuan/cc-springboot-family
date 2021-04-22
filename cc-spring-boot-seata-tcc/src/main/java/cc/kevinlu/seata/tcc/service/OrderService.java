package cc.kevinlu.seata.tcc.service;

public interface OrderService {

    String order(String userId, String commodityCode, int count);

    boolean orderTcc(String userId, String commodityCode, int count);

}
