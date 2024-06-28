package com.anhntv.ecom.entities;

import com.anhntv.ecom.constants.OrderStatus;
import com.anhntv.ecom.dto.OrderDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "orders")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_description")
    private String orderDescription;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "total_amount")
    private Long totalAmount;

    @Column(name = "discount")
    private Long discount;

    @Column(name = "tracking_id")
    private UUID trackingId;


    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    private Coupon coupon;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<CartItems> cartItems;

    public OrderDTO getOrderDTO() {
        OrderDTO dto = new OrderDTO();
        dto.setId(this.id);
        dto.setOrderDescription(this.orderDescription);
        dto.setAddress(this.address);
        dto.setPhone(this.phone);
        dto.setTrackingId(this.trackingId);
        dto.setAmount(this.amount);
        dto.setOrderDate(this.orderDate);
        dto.setStatus(this.status);
        dto.setUserName(this.user.getName());
        if(this.coupon != null) {
            dto.setCouponName(this.coupon.getName());
        }
        return dto;
    }
}
