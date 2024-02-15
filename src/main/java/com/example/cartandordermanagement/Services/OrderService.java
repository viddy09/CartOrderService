package com.example.cartandordermanagement.Services;

import com.example.cartandordermanagement.DTOs.OrderDTO;
import com.example.cartandordermanagement.Models.*;
import com.example.cartandordermanagement.Repositories.CartRepo;
import com.example.cartandordermanagement.Repositories.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final CartRepo cartRepo;
    private final OrderRepo orderRepo;

    public OrderService(CartRepo cartRepo,
                        OrderRepo orderRepo) {
        this.cartRepo = cartRepo;
        this.orderRepo = orderRepo;
    }

    public OrderDTO placeOrder(String userId) {
        Optional<Cart> optionalCart = cartRepo.findByUserIdAndCartStatus(userId, CartStatus.InUse);
        OrderDTO orderDTO = null;
        Cart cart = null;
        if(optionalCart.isEmpty()){
            throw new RuntimeException("Cart not found!!!");
        }
        cart = optionalCart.get();

        Order order = this.createOrder(cart, userId);
        try{
            orderRepo.save(order);
        }catch (Exception e){
            throw new RuntimeException("Something went wrong!!!");
        }

        //Update Inventory

        orderDTO.setStatus(String.valueOf(OrderStatus.Order_Preparing));
        orderDTO.setOrderId(String.valueOf(order.getId()));
        orderDTO.setTotCost(order.getTotCost());
        orderDTO.setCreatedDate(new Date());

        return orderDTO;

    }

    public String getOrderStatus(String orderId) {
        Optional<Order> optionalOrder = orderRepo.findById(Long.valueOf(orderId));
        if(optionalOrder.isEmpty()){
            throw new RuntimeException("Order not found");
        }
        return String.valueOf(optionalOrder.get().getOrderStatus());
    }

    public double getCost(List<CartProduct> products){
        double totCost =0.0;
        for(CartProduct cartProduct: products){
            totCost = totCost + (cartProduct.getQuantity() * cartProduct.getCost());
        }
        return totCost;
    }

    public Order createOrder(Cart cart ,String userId){
        Order order = new Order();
        order.setCart(cart);
        order.setOrderStatus(OrderStatus.Order_Preparing);
        order.setPaymentStatus(PaymentStatus.Pending);
        order.setTotCost(this.getCost(cart.getProducts()));
        order.setAmountPaid(0.0);
        order.setUserId(userId);
        return order;
    }
}
