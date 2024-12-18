package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.request.CreateOrderRequest;
import com.example.demo.request.UpdateOrderRequest;

public interface OrderService {
    public OrderDto createOrder(CreateOrderRequest request);

    public OrderDto updateOrder(Long orderId, UpdateOrderRequest request);

    public OrderDto getOrderById(Long orderId);
}
