package com.anhntv.ecom.entities;

import com.anhntv.ecom.dto.CouponDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@Entity
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    private Long discount;

    private Date expiration;

    private OffsetDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = OffsetDateTime.now();
    }

    public CouponDTO getDTO() {
        CouponDTO dto = new CouponDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setCode(code);
        dto.setDiscount(discount);
        dto.setExpiration(expiration);
        return dto;
    }
}
