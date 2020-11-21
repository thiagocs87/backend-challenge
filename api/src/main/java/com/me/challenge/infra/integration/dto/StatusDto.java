package com.me.challenge.infra.integration.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.me.challenge.domain.OrderStatus;
import com.me.challenge.infra.deserializer.StatusDtoDeserializer;
import com.me.challenge.infra.serializer.StatusDtoSerializer;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Builder
@Getter
@JsonDeserialize(using = StatusDtoDeserializer.class)
@JsonSerialize(using = StatusDtoSerializer.class)
public class StatusDto {

    private final String orderId;
    private final int approvedItems;
    private final BigDecimal approvedAmount;
    private final List<OrderStatus> listStatus;
}
