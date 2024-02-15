package com.example.cartandordermanagement.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDTO {
    private String userId;
    private int quantity;
    private String productId;

}
