package com.anhntv.ecom.services.customer.cart;

import com.anhntv.ecom.constants.OrderStatus;
import com.anhntv.ecom.dto.AddProductInCartDTO;
import com.anhntv.ecom.dto.CartItemsDTO;
import com.anhntv.ecom.dto.OrderDTO;
import com.anhntv.ecom.entities.*;
import com.anhntv.ecom.exceptions.ValidationException;
import com.anhntv.ecom.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CouponRepository couponRepository;

    public ResponseEntity<?> addProductToCart(AddProductInCartDTO dto) {
        Order activeOrder = orderRepository.findByUserIdAndStatus(dto.getUserId(), OrderStatus.PENDING);
        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId
                (dto.getProductId(), activeOrder.getId(), dto.getUserId());

        if (optionalCartItems.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else {
            Optional<Product> optionalProduct = productRepository.findById(dto.getProductId());
            Optional<User> optionalUser = userRepository.findById(dto.getUserId());

            if (optionalProduct.isPresent() && optionalUser.isPresent()) {
                CartItems cart = new CartItems();
                cart.setProduct(optionalProduct.get());
                cart.setPrice(optionalProduct.get().getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrder(activeOrder);

                CartItems updatedCart = cartItemsRepository.save(cart);

                activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
                activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
                activeOrder.getCartItems().add(cart);

                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(cart);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Product not found");
            }
        }
    }

    public OrderDTO getCartByUserId(Long userId) {
        Order activeOrder = orderRepository.findByUserIdAndStatus(userId, OrderStatus.PENDING);
        List<CartItemsDTO> cartItemsDTOList = activeOrder.getCartItems().stream().map(CartItems::getCartDTO).collect(Collectors.toList());
        OrderDTO dto = new OrderDTO();
        dto.setAmount(activeOrder.getAmount());
        dto.setId(activeOrder.getId());
        dto.setStatus(activeOrder.getStatus());
        dto.setDiscount(activeOrder.getDiscount());
        dto.setTotalAmount(activeOrder.getTotalAmount());
        dto.setCartItems(cartItemsDTOList);
        if(activeOrder.getCoupon() != null) {
            dto.setCouponName(activeOrder.getCoupon().getName());
        }
        return dto;
    }

    public OrderDTO applyCoupon(Long userId, String code) {
        Order activeOrder = orderRepository.findByUserIdAndStatus(userId, OrderStatus.PENDING);
        Coupon coupon = couponRepository.findByCode(code).orElseThrow(() -> new ValidationException("Coupon code is not valid"));
        if(activeOrder.getDiscount() != null) {
            throw new ValidationException("Coupon already applied");
        } else {
            double discountAmount = ((coupon.getDiscount() / 100.0) * activeOrder.getTotalAmount());
            double netAmount = activeOrder.getTotalAmount() - discountAmount;
            activeOrder.setAmount((long)netAmount);
            activeOrder.setDiscount((long)discountAmount);
            activeOrder.setCoupon(coupon);
            orderRepository.save(activeOrder);
            return activeOrder.getOrderDTO();

        }
    }
}
