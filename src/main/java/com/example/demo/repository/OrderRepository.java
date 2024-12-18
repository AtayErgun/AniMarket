package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    // Müşteri bazlı siparişleri getir
    List<Order> findByCustomerId(Long customerId);

    // Satıcı bazlı siparişleri getir
    List<Order> findBySellerId(Long sellerId);
}
