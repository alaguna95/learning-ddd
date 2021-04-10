package org.alaguna.learningddd.input_data.training.application;

import org.alaguna.learningddd.input_data.training.domain.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TrainingCreator {

    private TrainingRepository trainingRepository;

    public TrainingCreator(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public void createTraining(CreateTrainingCommand command){

        TrainingId id = new TrainingId(UUID.randomUUID().toString());

        TrainingStart start = new TrainingStart(command.getStart());
        TrainingFinish end = new TrainingFinish(command.getFinish());
        TrainingPeriod period = new TrainingPeriod(start, end);

        Training training = new Training(id, period);

        trainingRepository.createTraining(training);
    }
}
