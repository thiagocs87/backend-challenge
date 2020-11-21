package com.me.challenge.infra.integration.service;

import com.me.challenge.domain.Order;
import com.me.challenge.infra.integration.dto.OrderDto;

import java.util.Optional;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
public interface OrderService {
    void createOrder(OrderDto orderDto);

    OrderDto getOrderDto(String orderId);

    Optional<Order> getOrder(String orderId);

    void deleteOrder(String orderId);

    void updateOrder(OrderDto orderDto);
}
