package com.me.challenge.infra.integration.service;

import com.me.challenge.domain.Order;
import com.me.challenge.domain.OrderStatus;
import com.me.challenge.domain.validator.Validator;
import com.me.challenge.infra.integration.converter.StatusDtoToOrderConverter;
import com.me.challenge.infra.integration.dto.StatusDto;
import com.me.challenge.infra.metrics.MetricsType;
import com.me.challenge.infra.metrics.OrderMetrics;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final List<Validator> validators;
    private final OrderService service;
    private final StatusDtoToOrderConverter statusDtoToOrderConverter;
    private final OrderMetrics metrics;


    @Override
    public StatusDto process(final StatusDto statusDto) {
        final Order savedOrder = service.getOrder(statusDto.getOrderId()).orElse(null);
        if(Objects.isNull(savedOrder)) {
            return buildStatusReturn(statusDto.getOrderId(), Arrays.asList(OrderStatus.NOT_FOUND));
        }
        if(statusDto.getListStatus().get(0) == OrderStatus.DISAPPROVED) {
            return buildStatusReturn(statusDto.getOrderId(), Arrays.asList(OrderStatus.DISAPPROVED));
        }
        final Order receivedOrder = statusDtoToOrderConverter.convert(statusDto);
        return buildStatusReturn(receivedOrder.getOrderId(), validateOrderStatus(savedOrder, receivedOrder));
    }

    private List<OrderStatus> validateOrderStatus(final Order savedOrder, final Order receivedOrder) {
        final List<OrderStatus> listStatus = new ArrayList<>();
        validators.forEach(validator -> {
            listStatus.add((OrderStatus) validator.validate(savedOrder, receivedOrder));
        });
        return listStatus;
    }

    private StatusDto buildStatusReturn(final String receivedOrderId, final List<OrderStatus> validatedOrderStatus) {
        if(validatedOrderStatus.size() == 1) {
            this.incrementMetric(validatedOrderStatus);
            return StatusDto.builder()
                    .listStatus(validatedOrderStatus)
                    .orderId(receivedOrderId)
                    .build();
        }
        validatedOrderStatus.remove(OrderStatus.APPROVED);
        this.incrementMetric(validatedOrderStatus);
        return StatusDto.builder()
                .listStatus(validatedOrderStatus)
                .orderId(receivedOrderId)
                .build();
    }

    private void incrementMetric(final List<OrderStatus> validatedOrderStatus) {
        MetricsType metricType = validatedOrderStatus.contains(OrderStatus.APPROVED) ? MetricsType.ORDER_APPROVED : validatedOrderStatus.contains(OrderStatus.DISAPPROVED) ? MetricsType.ORDER_DISAPPROVED : MetricsType.ORDER_APPROVED_WITH_DIFFERENCES;
        metrics.increment(metricType.toString());
    }
}
