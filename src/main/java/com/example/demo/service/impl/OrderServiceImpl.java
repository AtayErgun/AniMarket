package com.example.demo.service.impl;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Advertisement;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderStatus;
import com.example.demo.entity.User;
import com.example.demo.repository.AdvertisementRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.CreateOrderRequest;
import com.example.demo.request.UpdateOrderRequest;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Override
    public OrderDto createOrder(CreateOrderRequest request) {
        // Müşteri ve ilan bilgilerini kontrol et
        Optional<User> customer = userRepository.findById(request.getCustomerId());
        Optional<Advertisement> advertisement = advertisementRepository.findById(request.getAdvertisementId());

        if (customer.isEmpty() || advertisement.isEmpty()) {
            throw new IllegalArgumentException("Customer or Advertisement not found");
        }

        // Siparişi oluştur
        Order order = new Order();
        order.setCustomer(customer.get());
        order.setAdvertisement(advertisement.get());
        order.setSeller(advertisement.get().getSeller());
        order.setTotalPrice(request.getTotalPrice());
        order.setOrderStatus(OrderStatus.valueOf("PENDING"));

        Order savedOrder = orderRepository.save(order);
        return toDto(savedOrder);
    }

    @Override
    public OrderDto updateOrder(Long orderId, UpdateOrderRequest request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.setOrderStatus(OrderStatus.valueOf(request.getOrderStatus()));
        Order updatedOrder = orderRepository.save(order);

        return toDto(updatedOrder);
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        return toDto(order);
    }

    private OrderDto toDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomer().getId());
        dto.setSellerId(order.getSeller().getId());
        dto.setAdvertisementId(order.getAdvertisement().getId());
        dto.setAdvertisementName(order.getAdvertisement().getAd());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setOrderStatus(String.valueOf(order.getOrderStatus()));
        dto.setOrderDate(order.getOrderDate());
        return dto;
    }
}
