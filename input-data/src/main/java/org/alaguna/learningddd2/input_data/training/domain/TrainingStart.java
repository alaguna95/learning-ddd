package org.alaguna.learningddd2.input_data.training.domain;

import org.alaguna.learningddd2.shared.domain.LocalDateTimeValueObject;

import java.time.LocalDateTime;

public class TrainingStart extends LocalDateTimeValueObject {

    public TrainingStart(LocalDateTime value) {
        super(value);
        super.checkValueIsNotNull(value);
    }
}
