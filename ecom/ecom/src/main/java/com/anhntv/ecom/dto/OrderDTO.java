package com.anhntv.ecom.dto;

import com.anhntv.ecom.constants.OrderStatus;
import com.anhntv.ecom.entities.User;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDTO {

    private Long id;

    private String orderDescription;

    private Date orderDate;

    private Long amount;

    private String address;

    private String phone;

    private OrderStatus status;

    private Long totalAmount;

    private Long discount;

    private UUID trackingId;

    private String userName;

    private List<CartItemsDTO> cartItems;

    private String couponName;
}
