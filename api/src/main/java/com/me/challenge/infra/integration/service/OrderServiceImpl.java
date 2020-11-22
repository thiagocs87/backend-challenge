package com.me.challenge.infra.integration.service;

import com.me.challenge.domain.Order;
import com.me.challenge.domain.repository.OrderRepository;
import com.me.challenge.infra.exceptionhandler.ResourceNotFoundException;
import com.me.challenge.infra.exceptionhandler.UpdateOrderException;
import com.me.challenge.infra.exceptionhandler.CreateOrderException;
import com.me.challenge.infra.integration.converter.OrderToOrderDtoConverter;
import com.me.challenge.infra.integration.converter.OrderDtoToOrderConverter;
import com.me.challenge.infra.integration.dto.OrderDto;
import com.me.challenge.infra.metrics.MetricsType;
import com.me.challenge.infra.metrics.OrderMetrics;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final OrderDtoToOrderConverter dtoConverter;
    private final OrderToOrderDtoConverter orderConverter;
    private final OrderMetrics metrics;


    @Override
    public void createOrder(final OrderDto orderDto) {
        try {
            Order order = dtoConverter.convert(orderDto);
            order.generateNewOrderData();
            repository.save(order);
            metrics.increment(MetricsType.ORDER_CREATED.toString());
        } catch (DuplicateKeyException ex) {
            throw new CreateOrderException("Pedido já existe na base");
        } catch (Exception e) {
            throw new CreateOrderException("Erro ao criar pedido");
        }
    }

    @Override
    public OrderDto getOrderDto(final String orderId) {
        Order order = getOrder(orderId).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
        return orderConverter.convert(order);
    }

    @Override
    public void deleteOrder(final String orderId) {
        Order order = getOrder(orderId).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
        repository.delete(order);
        metrics.increment(MetricsType.ORDER_DELETED.toString());
    }

    @Override
    public void updateOrder(final OrderDto orderDto) {
        try {
            Order receivedOrder = dtoConverter.convert(orderDto);
            Order savedOrder = getOrder(receivedOrder.getOrderId()).orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
            savedOrder.updateOrderData(receivedOrder);
            repository.save(savedOrder);
        } catch (ResourceNotFoundException ex){
            throw ex;
        } catch (Exception ex) {
            throw new UpdateOrderException("Erro ao atualizar pedido");
        }
    }

    @Override
    public Optional<Order> getOrder(final String orderId) {
        return repository.findByOrderId(orderId);
    }
}
