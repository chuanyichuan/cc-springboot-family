package com.example.image.extractor.events.order;

public interface OrderService {

    String order();

    void paying(String orderId);

    void payed(String orderId);

    void logistics(String orderId);

    void completed(String orderId);

}
