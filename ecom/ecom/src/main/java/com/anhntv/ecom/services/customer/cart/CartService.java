package com.anhntv.ecom.services.customer.cart;

import com.anhntv.ecom.dto.AddProductInCartDTO;
import com.anhntv.ecom.dto.OrderDTO;
import org.springframework.http.ResponseEntity;

public interface CartService {

    ResponseEntity<?> addProductToCart(AddProductInCartDTO dto);

    OrderDTO getCartByUserId(Long userId);

    OrderDTO applyCoupon(Long userId, String code);
}
