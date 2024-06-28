package com.anhntv.ecom.entities;


import com.anhntv.ecom.dto.CartItemsDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long price;

    private Long quantity;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public CartItemsDTO getCartDTO() {
        CartItemsDTO dto = new CartItemsDTO();
        dto.setId(id);
        dto.setPrice(price);
        dto.setProductId(product.getId());
        dto.setQuantity(quantity);
        dto.setUserId(user.getId());
        dto.setProductName(product.getName());
        dto.setReturnedImg(product.getImg());
        return dto;
    }
}
