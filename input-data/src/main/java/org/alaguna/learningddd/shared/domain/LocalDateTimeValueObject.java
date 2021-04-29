package org.alaguna.learningddd.shared.domain;

import java.time.LocalDateTime;
import java.util.Objects;


public abstract class LocalDateTimeValueObject {
    private LocalDateTime value;

    public LocalDateTimeValueObject() {
    }

    public LocalDateTimeValueObject(LocalDateTime value) {
        this.value = value;
    }

    public LocalDateTime value() {
        return value;
    }

    public void checkValueIsNotNull(LocalDateTime value){
        if(value == null){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return this.value().toString();
    } //TODO: WHEN NULL --> NullPointer

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LocalDateTimeValueObject)) {
            return false;
        }
        LocalDateTimeValueObject that = (LocalDateTimeValueObject) o;
        return Objects.equals(value, that.value);
    }


    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
