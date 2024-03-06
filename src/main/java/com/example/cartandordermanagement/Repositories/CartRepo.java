package com.example.cartandordermanagement.Repositories;

import com.example.cartandordermanagement.Models.Cart;
import com.example.cartandordermanagement.Models.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserIdAndCartStatus(String userId, CartStatus cartStatus);
    @Override
    Cart save(Cart cart);
}
