package org.alaguna.learningddd.input_data.training.domain;

import org.alaguna.learningddd.shared.domain.LocalDateTimeValueObject;

import java.time.LocalDateTime;

public class TrainingFinish extends LocalDateTimeValueObject {

    public TrainingFinish(LocalDateTime value) {
        super(value);

        if(value == null){
            throw new IllegalArgumentException();
        }
    }
}
