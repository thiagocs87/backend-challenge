package com.me.challenge.infra.serializer;

import com.me.challenge.infra.integration.dto.OrderDto;

import java.io.IOException;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
public class OrderDtoSerializer extends AbstractSerializer<OrderDto>{

    @Override
    public void serialize(final OrderDto orderDto, final JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField(SerializationLabels.ORDER, String.valueOf(orderDto.getOrderId()));
        jsonWriter.writeArrayFieldStart(SerializationLabels.ITEMS);
        orderDto.getItems().forEach(item -> {
            try {
                jsonWriter.writeStartObject();
                jsonWriter.writeStringField(SerializationLabels.DESCRIPTION, item.getDescription());
                jsonWriter.writeStringField(SerializationLabels.UNIT_PRICE, String.valueOf(item.getUnitPrice()));
                jsonWriter.writeStringField(SerializationLabels.QUANTITY, String.valueOf(item.getQuantity()));
                jsonWriter.writeEndObject();
            } catch (IOException e) {
                System.out.println(e);
            }
        });
        jsonWriter.writeEndArray();
        jsonWriter.writeEndObject();
    }

}
