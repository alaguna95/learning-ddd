package org.alaguna.learningddd.input_data.training.domain;


import java.time.LocalDateTime;
import org.alaguna.shared.domain.LocalDateTimeValueObject;

public class TrainingFinish extends LocalDateTimeValueObject {

    public TrainingFinish(LocalDateTime value) {
        super(value);

        if(value == null){
            throw new IllegalArgumentException();
        }
    }
}
