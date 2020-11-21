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
public class OrderDtoToOrderConverter implements Converter<Order, OrderDto> {
    @Override
    public Order convert(OrderDto orderDto) {
        return Order.builder()
                .orderId(orderDto.getOrderId())
                .items(orderDto.getItems())
                .build();
    }
}
