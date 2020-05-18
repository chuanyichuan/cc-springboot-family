package cc.kevinlu.image.extractor.events.order;

import org.springframework.context.ApplicationEvent;

public class OrderEvent extends ApplicationEvent {

    private String           orderId;

    // 0: 未付款, 1: 付款中, 2: 已付款, 3: 待通知物流, 4: 已通知物流, 5: 已发单
    private OrderStatusEnums status;

    public OrderEvent(Object source, String orderId, OrderStatusEnums status) {
        super(source);
        this.orderId = orderId;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderStatusEnums getStatus() {
        return status;
    }
}
