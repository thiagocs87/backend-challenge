package com.me.challenge.infra.integration.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.me.challenge.domain.OrderStatus;
import com.me.challenge.infra.deserializer.StatusDtoDeserializer;
import com.me.challenge.infra.serializer.StatusDtoSerializer;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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

    @NotNull
    private final String orderId;
    @NotNull
    private final int approvedItems;
    @NotNull
    @DecimalMin(value = "0.0")
    private final BigDecimal approvedAmount;
    @NotNull
    private final List<OrderStatus> listStatus;
}
