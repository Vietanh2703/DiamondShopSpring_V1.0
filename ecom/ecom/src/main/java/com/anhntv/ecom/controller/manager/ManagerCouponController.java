package com.anhntv.ecom.controller.manager;


import com.anhntv.ecom.dto.CouponDTO;
import com.anhntv.ecom.dto.ProductDTO;
import com.anhntv.ecom.entities.Coupon;
import com.anhntv.ecom.exceptions.ValidationException;
import com.anhntv.ecom.services.manager.coupon.ManagerCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manager")
public class ManagerCouponController {

    private final ManagerCouponService managerCouponService;

    @PostMapping("/post-coupon")
    public ResponseEntity<CouponDTO> createCoupon(@ModelAttribute CouponDTO dto) {
            CouponDTO createdCoupon = managerCouponService.createCoupon(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCoupon);
    }

    @GetMapping("/coupons")
    public ResponseEntity<List<CouponDTO>> getAllCoupons() {
        List<CouponDTO> dtos = managerCouponService.getAllCoupons();
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/coupon/{couponId}")
    public ResponseEntity<Void> deleteExpiredCoupons() {
        managerCouponService.deleteExpiredCoupons();
        return ResponseEntity.noContent().build();
    }
}
