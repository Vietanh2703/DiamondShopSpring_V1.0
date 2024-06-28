package com.anhntv.ecom.repository;

import com.anhntv.ecom.constants.OrderStatus;
import com.anhntv.ecom.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByUserIdAndStatus(Long userId, OrderStatus status);
}
