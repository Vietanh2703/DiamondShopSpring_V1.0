package com.anhntv.ecom.services.manager.coupon;

import com.anhntv.ecom.dto.CouponDTO;

import java.util.List;

public interface ManagerCouponService {
    CouponDTO createCoupon(CouponDTO dto);

    List<CouponDTO> getAllCoupons();

    void deleteExpiredCoupons();
}
