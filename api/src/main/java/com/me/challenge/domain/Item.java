package com.me.challenge.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@Builder
@Getter
public class Item {

    private final String description;
    private final BigDecimal unitPrice;
    private final int quantity;

}
