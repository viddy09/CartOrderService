package com.example.cartandordermanagement.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class CartProduct extends BaseModel{
    @ManyToOne
    @JsonManagedReference
    private Cart cart;
    private String productId;
    private int quantity;
    private double cost;
}
