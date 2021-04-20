package org.alaguna.learningddd.objects;

import org.alaguna.learningddd.input_data.training.application.TrainingCreateCommand;

import java.time.LocalDateTime;
import java.util.UUID;

public class TrainingCreateCommandCreator {

    public static TrainingCreateCommand create(){
         return new TrainingCreateCommand(UUID.randomUUID().toString(), LocalDateTime.of(2020,1,1,16,30),
                 LocalDateTime.of(2020, 1, 1, 17, 0));
    }
}
