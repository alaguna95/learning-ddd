package org.alaguna.input_data.training.application;

import org.alaguna.input_data.training.domain.*;
import org.alaguna.shared.bus.EventBus;
import org.springframework.stereotype.Service;

@Service
public class TrainingCreate {

    private TrainingRepository trainingRepository;
    private TrainingValidator trainingValidator;
    private EventBus eventBus;

    public TrainingCreate(TrainingRepository trainingRepository, TrainingValidator trainingValidator, EventBus eventBus) {
        this.trainingRepository = trainingRepository;
        this.trainingValidator = trainingValidator;
        this.eventBus = eventBus;
    }

    public void createTraining(TrainingCreateCommand command){

        trainingValidator.checkTrainingCreate(command);

        TrainingId id = new TrainingId(command.getId());
        TrainingPeriod period = new TrainingPeriod(new TrainingStart(command.getStart()),
                new TrainingFinish(command.getFinish()));
        Training training = Training.create(id, period);

        trainingRepository.createTraining(training);
        eventBus.publish(training.pullDomainEvents());
    }
}
