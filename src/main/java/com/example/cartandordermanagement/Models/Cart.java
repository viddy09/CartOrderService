package com.example.cartandordermanagement.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart extends BaseModel{
    private String userId;
    @OneToMany(mappedBy = "cart")
    @JsonBackReference
    private List<CartProduct> products;
    @Enumerated(EnumType.ORDINAL)
    private CartStatus cartStatus;
}
