package com.example.image.extractor.events.order;

import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderEventListener implements ApplicationListener<OrderEvent> {

    @Resource
    private OrderService orderService;

    @Override
    public void onApplicationEvent(OrderEvent event) {
        if (Objects.isNull(event)) {
            return;
        }
        OrderStatusEnums status = event.getStatus();
        String orderId = event.getOrderId();
        switch (status) {
            //            case ORDERED: orderService.order(); break;
            case PAYING:
                orderService.paying(orderId);
                break;
            case PAYED:
                orderService.payed(orderId);
                break;
            case NOTICE_LOGISTICS:
                orderService.logistics(orderId);
                break;
            case LOGISTICSED:
                orderService.completed(orderId);
                break;
            case COMPLETED:
                System.out.println("订单: " + orderId + " 发起评价通知!");
                break;
            default:
                break;
        }
    }

}
