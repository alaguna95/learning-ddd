package org.alaguna.learningddd.input_data.training.application;

import org.alaguna.learningddd.input_data.training.domain.*;

import java.time.LocalDateTime;
import java.util.UUID;

public class TrainingCreator {

    public void createTraining(CreateTrainingCommand command){

        TrainingId id = new TrainingId(command.getId());

        TrainingStart start = new TrainingStart(command.getStart());
        TrainingEnd end = new TrainingEnd(command.getEnd());
        TrainingPeriod period = new TrainingPeriod(start, end);

        Training training = new Training(id, period);

    }
}
