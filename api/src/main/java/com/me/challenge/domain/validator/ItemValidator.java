package com.me.challenge.domain.validator;

import com.me.challenge.domain.Order;
import com.me.challenge.domain.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Component
public class ItemValidator implements Validator<OrderStatus, Order, Order>{
    @Override
    public OrderStatus validate(Order savedOrder, Order receivedOrder) {
        BiFunction<Integer, Integer, OrderStatus> itemBiPredicate = (a, b) -> a == b ? OrderStatus.APPROVED : a < b ? OrderStatus.APPROVED_GREATER_QUANTITY : OrderStatus.APPROVED_LOWER_QUANTITY;
        return itemBiPredicate.apply(savedOrder.getTotalItems(), receivedOrder.getTotalItems());
    }
}