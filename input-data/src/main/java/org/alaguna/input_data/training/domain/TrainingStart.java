package org.alaguna.input_data.training.domain;

import java.time.LocalDateTime;
import org.alaguna.shared.domain.LocalDateTimeValueObject;

public class TrainingStart extends LocalDateTimeValueObject {

    public TrainingStart(LocalDateTime value) {
        super(value);
        super.checkValueIsNotNull(value);
    }
}
