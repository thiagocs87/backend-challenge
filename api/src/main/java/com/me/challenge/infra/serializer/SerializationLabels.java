package com.me.challenge.infra.serializer;

import lombok.AllArgsConstructor;

/**
 *
 * @author Thiago Santos
 * @version 1.0 18/11/2020
 */
@AllArgsConstructor
public enum SerializationLabels {
    ORDER("pedido"),
    DESCRIPTION("descricao"),
    UNIT_PRICE("precoUnitario"),
    QUANTITY("qtd"),
    STATUS("status"),
    APPROVED_ITEMS("itensAprovados"),
    APPROVED_AMOUNT("valorAprovado"),
    ITEMS("itens");

    private final String label;

    @Override
    public String toString() {
        return this.label;
    }
}
