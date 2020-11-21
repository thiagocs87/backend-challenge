package com.me.challenge.infra.deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.me.challenge.domain.Item;
import com.me.challenge.infra.exceptionhandler.ConvertException;
import com.me.challenge.infra.integration.dto.OrderDto;
import com.me.challenge.infra.serializer.SerializationLabels;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Component
@RequiredArgsConstructor
public class OrderDtoDeserializer extends AbstractDeserializer<OrderDto> {

    private final ItemDeserializer itemDeserializer;

    @Override
    public OrderDto deserialize(final JsonNode jsonNode) {
        try {
            final String orderId = getFieldTextValue(jsonNode, SerializationLabels.ORDER);
            final List<Item> items = itemDeserializer.deserialize(jsonNode.get(SerializationLabels.ITEMS.toString()));
            return OrderDto.builder()
                    .orderId(orderId)
                    .items(items)
                    .build();
        } catch (Exception ex) {
            throw new ConvertException("Erro ao validar as informaçoes");
        }

    }

}
