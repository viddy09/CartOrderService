package com.example.cartandordermanagement.Controllers;

import com.example.cartandordermanagement.DTOs.CartDTO;
import com.example.cartandordermanagement.DTOs.CartDetailsDTO;
import com.example.cartandordermanagement.Services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    //Add product to cart
    @PostMapping("/add")
    public ResponseEntity<String> addProductToCart(@RequestBody CartDTO cartDTO){
        ResponseEntity<String> responseEntity;
        String msg = "";
        try{
            msg = cartService.addProductToCart(cartDTO.getUserId(),cartDTO.getProductId(),cartDTO.getQuantity());
            responseEntity = new ResponseEntity<>(msg, HttpStatus.OK);
        }catch (Exception e){
            msg = e.getMessage();
        }
        responseEntity = new ResponseEntity<>(msg, HttpStatus.OK);
        return responseEntity;
    }

    //Remove Product From the cart
    @PostMapping("/remove")
    public ResponseEntity<String> removeProductFromCart(@RequestBody CartDTO cartDTO){
        ResponseEntity<String> responseEntity;
        String msg = "";
        try{
            msg = cartService.removeProductFromCart(cartDTO.getUserId(),cartDTO.getProductId(),cartDTO.getQuantity());
            responseEntity = new ResponseEntity<>(msg, HttpStatus.OK);
        }catch (Exception e){
            msg = e.getMessage();
        }
        responseEntity = new ResponseEntity<>(msg, HttpStatus.OK);
        return responseEntity;
    }

    //Get Products from Cart
    @GetMapping("/{id}")
    public ResponseEntity<CartDetailsDTO> getCart(@PathVariable("id") String userId){
        ResponseEntity<CartDetailsDTO> responseEntity;
        CartDetailsDTO cartDetailsDTO = null;
        try{
            cartDetailsDTO = cartService.getCartDetails(userId);
            responseEntity = new ResponseEntity<>(cartDetailsDTO, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("CartDetails  "+ e.getMessage());
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
