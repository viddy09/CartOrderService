package com.example.cartandordermanagement.Repositories;

import com.example.cartandordermanagement.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepo extends JpaRepository<Order, Long> {
    Optional<Order> findById(Long orderId);
}
