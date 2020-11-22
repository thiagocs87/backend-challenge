package com.me.challenge.infra.integration.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.me.challenge.infra.deserializer.OrderDtoDeserializer;
import com.me.challenge.domain.Item;
import com.me.challenge.infra.serializer.OrderDtoSerializer;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Builder
@Getter
@JsonDeserialize(using = OrderDtoDeserializer.class)
@JsonSerialize(using = OrderDtoSerializer.class)
public class OrderDto {

    @NotNull
    private final String orderId;
    @NotNull
    @Valid
    private final List<Item> items;

}
