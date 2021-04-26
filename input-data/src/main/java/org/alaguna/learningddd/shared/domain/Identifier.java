package org.alaguna.learningddd.shared.domain;


import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@EqualsAndHashCode
@ToString
public abstract class Identifier implements Serializable {
    final protected String value;

    public Identifier(String value) {
        //ensureValidUuid(value);

        this.value = value;
    }


    public String value() {
        return value;
    }


    private void ensureValidUuid(String value) throws IllegalArgumentException {
        UUID.fromString(value);
    }
}