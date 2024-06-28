package com.anhntv.ecom.controller.customer;

import com.anhntv.ecom.dto.AddProductInCartDTO;
import com.anhntv.ecom.dto.OrderDTO;
import com.anhntv.ecom.exceptions.ValidationException;
import com.anhntv.ecom.services.customer.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDTO dto) {
        return cartService.addProductToCart(dto);
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> getCartByUserId(@PathVariable Long userId) {
        OrderDTO dto = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/coupon/{userId}/{code}")
    public ResponseEntity<?> applyCoupon(@PathVariable Long userId, @PathVariable String code) {
        try {
            OrderDTO dto = cartService.applyCoupon(userId, code);
            return ResponseEntity.ok(dto);
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
