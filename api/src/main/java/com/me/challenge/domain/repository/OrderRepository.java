package com.me.challenge.domain.repository;

import com.me.challenge.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    Optional<Order> findByOrderId(final String orderId);
    void removeByOrderId(final int orderId);
}
