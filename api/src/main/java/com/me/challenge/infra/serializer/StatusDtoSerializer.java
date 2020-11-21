package com.me.challenge.infra.serializer;

import com.me.challenge.infra.integration.dto.StatusDto;

import java.io.IOException;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
public class StatusDtoSerializer extends AbstractSerializer<StatusDto>{

    @Override
    public void serialize(final StatusDto statusDto, final JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField(SerializationLabels.ORDER, String.valueOf(statusDto.getOrderId()));
        jsonWriter.writeArrayFieldStart(SerializationLabels.STATUS);
        statusDto.getListStatus().forEach(status -> {
            try {
                jsonWriter.writeStringField(status.getLabel());
            } catch (IOException e) {
            }
        });
        jsonWriter.writeEndArray();
        jsonWriter.writeEndObject();
    }

}
