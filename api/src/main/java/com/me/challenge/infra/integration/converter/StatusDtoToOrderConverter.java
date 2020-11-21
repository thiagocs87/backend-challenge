package com.me.challenge.infra.integration.converter;

import com.me.challenge.domain.Order;
import com.me.challenge.infra.integration.dto.StatusDto;
import org.springframework.stereotype.Component;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Component
public class StatusDtoToOrderConverter implements Converter<Order, StatusDto> {
    @Override
    public Order convert(StatusDto statusDto) {
        return Order.builder()
                .orderId(statusDto.getOrderId())
                .totalPrice(statusDto.getApprovedAmount())
                .totalItems(statusDto.getApprovedItems())
                .status(statusDto.getListStatus())
                .build();
    }
}
