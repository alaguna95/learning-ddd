package org.alaguna.learningddd.input_data.training.application;

import org.alaguna.learningddd.input_data.training.domain.*;
import org.alaguna.learningddd.shared.domain.bus.EventBus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TrainingCreator {

    private TrainingRepository trainingRepository;
    private EventBus eventBus;

    public TrainingCreator(TrainingRepository trainingRepository, EventBus eventBus) {
        this.trainingRepository = trainingRepository;
        this.eventBus = eventBus;
    }

    public void createTraining(TrainingCreateCommand command){

        if(trainingRepository.existSomeTrainingInThisPeriod(command.getStart(), command.getFinish())){
            throw new IllegalArgumentException();
        }

        TrainingId id = new TrainingId(command.getId());
        TrainingPeriod period = new TrainingPeriod(new TrainingStart(command.getStart()),
                new TrainingFinish(command.getFinish()));
        Training training = Training.create(id, period);

        trainingRepository.createTraining(training);
        eventBus.publish(training.pullDomainEvents());
    }
}
