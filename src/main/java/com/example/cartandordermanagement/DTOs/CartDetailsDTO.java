package com.example.cartandordermanagement.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartDetailsDTO {
    private String userId;
    private List<ProductDTO> products;
    @Getter
    @Setter
    public static class ProductDTO{
        private Long productId;
        private int quantity;
    }
}
