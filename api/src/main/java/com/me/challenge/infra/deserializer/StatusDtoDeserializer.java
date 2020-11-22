package com.me.challenge.infra.deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.me.challenge.domain.OrderStatus;
import com.me.challenge.infra.exceptionhandler.ConvertException;
import com.me.challenge.infra.integration.dto.StatusDto;
import com.me.challenge.infra.serializer.SerializationLabels;
import com.me.challenge.infra.validator.StatusDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.xml.validation.Validator;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Set;

/**
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Component
@RequiredArgsConstructor
public class StatusDtoDeserializer extends AbstractDeserializer<StatusDto> {

    private final StatusDtoValidator validator;

    @Override
    public StatusDto deserialize(final JsonNode jsonNode) {
        try {
            final String orderId = getFieldTextValue(jsonNode, SerializationLabels.ORDER);
            final int approvedItems = getFieldIntegerValue(jsonNode, SerializationLabels.APPROVED_ITEMS);
            final BigDecimal approvedAmount = getFieldBigDecimalValue(jsonNode, SerializationLabels.APPROVED_AMOUNT);
            final OrderStatus status = Arrays.stream(OrderStatus.values()).filter(s-> s.getLabel().equalsIgnoreCase((getFieldTextValue(jsonNode, SerializationLabels.STATUS)))).findAny().orElseThrow(RuntimeException::new);

            final StatusDto statusDto =
                    StatusDto.builder()
                            .orderId(orderId)
                            .listStatus(Arrays.asList(status))
                            .approvedAmount(approvedAmount)
                            .approvedItems(approvedItems)
                            .build();
            if (!validator.validate(statusDto).isEmpty()) {
                throw new RuntimeException();
            }
            return statusDto;
        } catch (Exception ex) {
            throw new ConvertException("Erro ao validar as informaçoes");
        }
    }

}
