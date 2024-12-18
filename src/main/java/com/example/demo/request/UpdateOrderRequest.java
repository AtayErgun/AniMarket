package com.example.demo.request;

import com.example.demo.entity.OrderStatus;

public class UpdateOrderRequest {
    private Long orderId;
    private String orderStatus;

    // Getters and Setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = String.valueOf(orderStatus);
    }
}
