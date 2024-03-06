package com.example.cartandordermanagement.Repositories;

import com.example.cartandordermanagement.Models.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartProductRepo extends JpaRepository<CartProduct, Long> {
    Optional<CartProduct> findByCart_IdAndProductId(Long cartId, String productId);

    Optional<List<CartProduct>> findByCart_Id(Long cartId);
    @Override
    CartProduct save(CartProduct cartProduct);
}
