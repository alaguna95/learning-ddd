package org.alaguna.shared.domain;

import java.util.Objects;

public class IntegerValueObject {

    private Integer value;

    public IntegerValueObject(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IntegerValueObject)) {
            return false;
        }
        IntegerValueObject that = (IntegerValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
