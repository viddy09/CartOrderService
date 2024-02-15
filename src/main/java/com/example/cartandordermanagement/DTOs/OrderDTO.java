package com.example.cartandordermanagement.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderDTO {
//    private CartDetailsDTO cartDetailsDTO;
    private double totCost;
    private String status;
    private Date createdDate;
    private String orderId;

}
