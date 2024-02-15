package com.example.cartandordermanagement.Models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderHistory extends BaseModel{
    private String userId;
    private Long OrderId;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
}
