package org.alaguna.learningddd.input_data.training.application;

import org.alaguna.learningddd.input_data.training.domain.*;
import org.alaguna.learningddd.shared.domain.bus.EventBus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TrainingCreator {

    private TrainingRepository trainingRepository;
    private TrainingValidator trainingValidator;
    private EventBus eventBus;

    public TrainingCreator(TrainingRepository trainingRepository, TrainingValidator trainingValidator, EventBus eventBus) {
        this.trainingRepository = trainingRepository;
        this.trainingValidator = trainingValidator;
        this.eventBus = eventBus;
    }

    public void createTraining(TrainingCreateCommand command){

        trainingValidator.checkTrainingCreate(command);

        TrainingId id = new TrainingId(UUID.randomUUID().toString());
        TrainingPeriod period = new TrainingPeriod(new TrainingStart(command.getStart()),
                new TrainingFinish(command.getFinish()));
        Training training = Training.create(id, period);

        trainingRepository.createTraining(training);
        eventBus.publish(training.pullDomainEvents());
    }
}
