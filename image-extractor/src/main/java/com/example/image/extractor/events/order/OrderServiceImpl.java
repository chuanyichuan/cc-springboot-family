package com.example.image.extractor.events.order;

import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService, ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public String order() {
        String orderId = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println("下单成功，订单号：" + orderId);
        publisherEvent(orderId, OrderStatusEnums.PAYING);
        return orderId;
    }

    @Override
    public void paying(String orderId) {
        System.out.println("订单: " + orderId + " 发起支付");
        publisherEvent(orderId, OrderStatusEnums.PAYED);
    }

    @Override
    public void payed(String orderId) {
        System.out.println("订单: " + orderId + " 支付完成");
        publisherEvent(orderId, OrderStatusEnums.NOTICE_LOGISTICS);
    }

    @Override
    public void logistics(String orderId) {
        System.out.println("订单: " + orderId + " 物流发送完毕!");
        publisherEvent(orderId, OrderStatusEnums.LOGISTICSED);
    }

    @Override
    public void completed(String orderId) {
        System.out.println("订单: " + orderId + " 完成,等待评价");
        publisherEvent(orderId, OrderStatusEnums.COMPLETED);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publisherEvent(String orderId, OrderStatusEnums statusEnums) {
        OrderEvent orderEvent = new OrderEvent(orderId, orderId, statusEnums);
        this.applicationEventPublisher.publishEvent(orderEvent);
    }

}
