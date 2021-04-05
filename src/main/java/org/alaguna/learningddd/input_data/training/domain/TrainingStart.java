package org.alaguna.learningddd.input_data.training.domain;

import org.alaguna.learningddd.shared.LocalDateTimeValueObject;

import java.time.LocalDateTime;

public class TrainingStart extends LocalDateTimeValueObject {

    public TrainingStart(LocalDateTime value) {
        super(value);
        super.checkValueIsNotNull(value);
    }
}
