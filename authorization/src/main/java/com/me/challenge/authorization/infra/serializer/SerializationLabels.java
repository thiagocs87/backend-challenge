package com.me.challenge.authorization.infra.serializer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SerializationLabels {
    LOGIN("login"),
    PASSWORD("password");

    private final String label;

    @Override
    public String toString() {
        return this.label;
    }
}
