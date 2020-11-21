package com.me.challenge.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Getter
@Builder
@Document(collection = "orders")
public class Order {

    @Id
    private String id;
    @Indexed(unique = true)
    private String orderId;
    private List<Item> items;
    private int totalItems;
    private BigDecimal totalPrice;
    private List<OrderStatus> status;

    public void generateNewOrderData() {
        this.id = UUID.randomUUID().toString();
        this.status = Arrays.asList(OrderStatus.CREATED);
        this.calculateTotalPrice();
        this.calculateTotalQuantity();
    }

    private void calculateTotalQuantity() {
        this.totalItems = 0;
        items.forEach(item -> this.totalItems += item.getQuantity());
    }

    private void calculateTotalPrice() {
        this.totalPrice = BigDecimal.ZERO;
        items.forEach(item -> {
            this.totalPrice = this.totalPrice.add(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        });
    }

    public void updateOrderData(Order orderReceived) {
        this.items = orderReceived.getItems();
        this.calculateTotalPrice();
        this.calculateTotalQuantity();
    }
}
