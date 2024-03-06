package com.example.cartandordermanagement.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class CartProduct extends BaseModel{
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cart_id", nullable=false)
    private Cart cart;
    private String productId;
    private int quantity;
    private double cost;
}
