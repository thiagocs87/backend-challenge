package com.me.challenge.infra.deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.me.challenge.domain.Item;
import com.me.challenge.infra.serializer.SerializationLabels;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Component
@RequiredArgsConstructor
public class ItemDeserializer extends AbstractDeserializer<List<Item>> {


    @Override
    public List<Item> deserialize(final JsonNode jsonNode) {
        List<Item> items = new ArrayList<>();
        jsonNode.forEach(jsonItem -> {
            items.add(
                    Item.builder()
                            .description(getFieldTextValue(jsonItem, SerializationLabels.DESCRIPTION))
                            .quantity(getFieldIntegerValue(jsonItem, SerializationLabels.QUANTITY))
                            .unitPrice(getFieldBigDecimalValue(jsonItem, SerializationLabels.UNIT_PRICE))
                            .build());
        });
        return items;
    }
}
