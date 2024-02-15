package com.example.cartandordermanagement.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseModel{
    private double totCost;
    private double amountPaid;
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;
    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;
    private String userId;
}
