package com.me.challenge.domain.validator;

import com.me.challenge.domain.Order;
import com.me.challenge.domain.OrderStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.BiFunction;

@Component
public class TotalPriceValidator implements Validator<OrderStatus, Order, Order> {
    @Override
    public OrderStatus validate(Order savedOrder, Order receivedOrder) {
        BiFunction<BigDecimal, BigDecimal, OrderStatus> itemBiPredicate = (a, b) -> a.compareTo(b) == 0 ? OrderStatus.APPROVED : a.compareTo(b) == -1 ? OrderStatus.APPROVED_GREATER_VALUE : OrderStatus.APPROVED_LOWER_VALUE;
        return itemBiPredicate.apply(savedOrder.getTotalPrice(), receivedOrder.getTotalPrice());
    }
}