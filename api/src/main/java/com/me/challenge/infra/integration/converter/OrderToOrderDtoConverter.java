package com.me.challenge.infra.integration.converter;

import com.me.challenge.domain.Order;
import com.me.challenge.infra.integration.dto.OrderDto;
import org.springframework.stereotype.Component;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Component
public class OrderToOrderDtoConverter implements Converter<OrderDto, Order> {
    @Override
    public OrderDto convert(Order order) {
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .items(order.getItems())
                .build();
    }
}
