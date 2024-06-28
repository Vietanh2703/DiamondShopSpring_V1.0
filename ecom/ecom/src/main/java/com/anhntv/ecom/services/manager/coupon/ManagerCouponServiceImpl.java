package com.anhntv.ecom.services.manager.coupon;

import com.anhntv.ecom.dto.CouponDTO;
import com.anhntv.ecom.entities.Coupon;
import com.anhntv.ecom.exceptions.ValidationException;
import com.anhntv.ecom.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerCouponServiceImpl implements ManagerCouponService{

    private final CouponRepository couponRepository;

    public CouponDTO createCoupon(CouponDTO dto) {
        if(couponRepository.existsByCode(dto.getCode())) {
            throw new ValidationException("Coupon code is already exists");
        } else {
            Coupon coupon = new Coupon();
            coupon.setName(dto.getName());
            coupon.setCode(dto.getCode());
            coupon.setDiscount(dto.getDiscount());
            coupon.setExpiration(dto.getExpiration());
            return couponRepository.save(coupon).getDTO();
        }
    }


    public List<CouponDTO> getAllCoupons() {
        List<Coupon> coupons = couponRepository.findAll();
        return coupons.stream().map(Coupon::getDTO).collect(Collectors.toList());
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredCoupons() {
        List<Coupon> coupons = couponRepository.findAll();
        Date currentDate = new Date();
        for (Coupon coupon : coupons) {
            if (coupon.getExpiration().before(currentDate)) {
                couponRepository.delete(coupon);
            }
        }
    }
}
