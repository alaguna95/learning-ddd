package org.alaguna.learningddd2.shared.domain;

import java.util.Objects;

public abstract class BooleanValueObject {
    private Boolean value;

    public BooleanValueObject(Boolean value) {
        this.value = value;
    }

    public Boolean value() {
        return value;
    }

    @Override
    public String toString() {
        return this.value().toString(); //TODO: WHEN IS NULL VALUE --> NULL POINTER.
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BooleanValueObject)) {
            return false;
        }
        BooleanValueObject that = (BooleanValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

