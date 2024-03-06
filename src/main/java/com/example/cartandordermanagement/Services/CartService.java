package com.example.cartandordermanagement.Services;

import com.example.cartandordermanagement.DTOs.CartDetailsDTO;
import com.example.cartandordermanagement.Models.Cart;
import com.example.cartandordermanagement.Models.CartProduct;
import com.example.cartandordermanagement.Models.CartStatus;
import com.example.cartandordermanagement.Repositories.CartProductRepo;
import com.example.cartandordermanagement.Repositories.CartRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepo cartRepo;
    private final CartProductRepo cartProductRepo;

    public CartService(CartRepo cartRepo, CartProductRepo cartProductRepo) {
        this.cartRepo = cartRepo;
        this.cartProductRepo = cartProductRepo;
    }

    public String addProductToCart(String userId, String productId, int quantity) {
        //Could cache the Active Cart Details
        //Get Latest Cart which is active
        Optional<Cart> optionalCart = cartRepo.findByUserIdAndCartStatus(userId, CartStatus.InUse);

        //Create cart if not created
        Cart cart = null;
        if(optionalCart.isEmpty()) {
            cart = new Cart();
            cart.setCartStatus(CartStatus.InUse);
            cart.setUserId(userId);
            try{
                cartRepo.save(cart);
            }
            catch (Exception e){
                System.out.println("Cart  "+ e.getMessage());
            }
        }
        else {
            cart = optionalCart.get();
        }

        CartProduct cartProduct = new CartProduct();
        cartProduct.setProductId(productId);
        cartProduct.setQuantity(quantity);
        try{
            cartProduct.setCart(cart);
            cartProductRepo.save(cartProduct);
        }catch (Exception e){
            System.out.println("CartProduct  "+ e.getMessage());
            return "Can't Save Product";
        }
        return "Product Added Successfully";
    }

    public String removeProductFromCart(String userId, String productId, int quantity) {
        //Could cache the Active Cart Details
        //Get Latest Cart which is active
        Optional<Cart> optionalCart = cartRepo.findByUserIdAndCartStatus(userId, CartStatus.InUse);
        if (optionalCart.isEmpty()){
            return "Can't Perform this action";
        }
        Optional<CartProduct> optionalCartProduct = cartProductRepo.findByCart_IdAndProductId(optionalCart.get().getId(), productId);
        if(optionalCartProduct.isEmpty()){
            return "Can't Perform this action";
        }
        CartProduct cartProduct = optionalCartProduct.get();
        cartProduct.setQuantity(cartProduct.getQuantity()-quantity);
        if(cartProduct.getQuantity() <= 0){
            cartProductRepo.delete(cartProduct);
        }
        else {
            cartProductRepo.save(cartProduct);
        }
        return "Action performed Successfully";
    }

    public CartDetailsDTO getCartDetails(String userId) {
        //Could cache the Active Cart Details
        //Get Latest Cart which is active
        Optional<Cart> optionalCart = cartRepo.findByUserIdAndCartStatus(userId, CartStatus.InUse);
        CartDetailsDTO cartDetailsDTO=null;
        if (optionalCart.isEmpty()){
            throw new RuntimeException("No Details to show");
        }
        Optional<List<CartProduct>> optionalCartProducts = cartProductRepo.findByCart_Id(optionalCart.get().getId());
        cartDetailsDTO = new CartDetailsDTO();
        List<CartDetailsDTO.ProductDTO> productDTOS = new ArrayList<>();
        for (CartProduct cartProduct: optionalCartProducts.get()){
            CartDetailsDTO.ProductDTO productDTO = new CartDetailsDTO.ProductDTO();
            productDTO.setProductId(cartProduct.getId());
            productDTO.setQuantity(cartProduct.getQuantity());
            productDTOS.add(productDTO);
        }
        cartDetailsDTO.setUserId(userId);
        cartDetailsDTO.setProducts(productDTOS);
        return cartDetailsDTO;
    }
}
