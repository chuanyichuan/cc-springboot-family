package com.example.image.extractor.events.order;

// 0: 未付款, 1: 付款中, 2: 已付款, 3: 待通知物流, 4: 已通知物流, 5: 已发单
public enum OrderStatusEnums {
    ORDERED,
    PAYING,
    PAYED,
    NOTICE_LOGISTICS,
    LOGISTICSED,
    COMPLETED;
}
