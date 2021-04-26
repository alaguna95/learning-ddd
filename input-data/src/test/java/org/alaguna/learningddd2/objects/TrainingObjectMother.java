package org.alaguna.learningddd2.objects;

import org.alaguna.learningddd2.input_data.training.domain.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class TrainingObjectMother {

    public static Training randomTraining(){

        TrainingId id = new TrainingId(UUID.randomUUID().toString());
        TrainingPeriod period = new TrainingPeriod(new TrainingStart(LocalDateTime.of(2020,1,1,17,30)),
                new TrainingFinish(LocalDateTime.of(2020,1,1,18,30)));

        return new Training(id, period);
    }
}
