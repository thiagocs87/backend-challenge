package com.me.challenge.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@AllArgsConstructor
public enum OrderStatus {
    CREATED("CRIADO"),
    APPROVED("APROVADO"),
    APPROVED_LOWER_VALUE("APROVADO_VALOR_A_MENOR"),
    APPROVED_GREATER_VALUE("APROVADO_VALOR_A_MAIOR"),
    APPROVED_GREATER_QUANTITY("APROVADO_QTD_A_MAIOR"),
    APPROVED_LOWER_QUANTITY ("APROVADO_QTD_A_MENOR"),
    NOT_FOUND("CODIGO_PEDIDO_INVALIDO"),
    DISAPPROVED("REPROVADO");

    @Getter
    private final String label;

    @Override
    public String toString() {
        return this.label;
    }

}
