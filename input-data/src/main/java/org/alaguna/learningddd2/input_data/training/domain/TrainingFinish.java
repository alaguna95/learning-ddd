package org.alaguna.learningddd2.input_data.training.domain;

import org.alaguna.learningddd2.shared.domain.LocalDateTimeValueObject;

import java.time.LocalDateTime;

public class TrainingFinish extends LocalDateTimeValueObject {

    public TrainingFinish(LocalDateTime value) {
        super(value);

        if(value == null){
            throw new IllegalArgumentException();
        }
    }
}
