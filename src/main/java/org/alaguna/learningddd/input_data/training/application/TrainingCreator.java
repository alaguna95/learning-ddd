package org.alaguna.learningddd.input_data.training.application;

import org.alaguna.learningddd.input_data.training.domain.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TrainingCreator {

    private TrainingRepository trainingRepository;
    private TrainingValidator trainingValidator;

    public TrainingCreator(TrainingRepository trainingRepository, TrainingValidator trainingValidator) {
        this.trainingRepository = trainingRepository;
        this.trainingValidator = trainingValidator;
    }

    public void createTraining(TrainingCreateCommand command){

        trainingValidator.checkTrainingCreate(command);

        TrainingId id = new TrainingId(UUID.randomUUID().toString());
        TrainingPeriod period = new TrainingPeriod(new TrainingStart(command.getStart()),
                new TrainingFinish(command.getFinish()));
        Training training = new Training(id, period);

        trainingRepository.createTraining(training);
    }
}
