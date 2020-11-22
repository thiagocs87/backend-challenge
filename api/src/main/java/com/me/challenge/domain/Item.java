package com.me.challenge.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Builder
@Getter
public class Item {

    @NotNull
    private final String description;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private final BigDecimal unitPrice;
    @NotNull
    @Positive
    private final int quantity;

    public static void main(String args[]) {
        Item item = Item.builder().quantity(0).build();
        System.out.println(item);
    }

}
