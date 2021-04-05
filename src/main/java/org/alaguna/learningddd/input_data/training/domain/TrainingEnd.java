package org.alaguna.learningddd.input_data.training.domain;

import java.time.LocalDateTime;

public class TrainingEnd extends LocalDateTimeValueObject{

    public TrainingEnd(LocalDateTime value) {
        super(value);

        if(value == null){
            throw new IllegalArgumentException();
        }
    }
}
