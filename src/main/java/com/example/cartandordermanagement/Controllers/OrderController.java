package com.example.cartandordermanagement.Controllers;

import com.example.cartandordermanagement.DTOs.OrderDTO;
import com.example.cartandordermanagement.Services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //Place Order
    @PostMapping("/placeOrder/{id}")
    public ResponseEntity<OrderDTO> placeOrder(@PathVariable("id") String userId){
        ResponseEntity<OrderDTO>  responseEntity;
        OrderDTO orderDTO = null;
        try{
            orderDTO = orderService.placeOrder(userId);
            responseEntity = new ResponseEntity<>(orderDTO, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Order Placed Unsuccessfull "+e.getMessage());
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //Order Status - To track the order
    @GetMapping("/status/{id}")
    public ResponseEntity<String> getOrderStatus(@PathVariable("id") String orderId){
        ResponseEntity<String> responseEntity;
        String status;
        try{
            status = orderService.getOrderStatus(orderId);
            responseEntity = new ResponseEntity<>(status, HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //Update Order Status
    @PostMapping("/updateStatus")
    public ResponseEntity<Void> updateOrderStatus(@RequestBody OrderDTO orderDTO){
        ResponseEntity<Void> responseEntity;
        try{
            orderService.updateOrderStatus(orderDTO.getOrderId(), orderDTO.getStatus());
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
