package org.alaguna.learningddd.input_data.training.application;

import org.alaguna.learningddd.input_data.training.domain.TrainingRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TrainingValidator {

    private TrainingRepository trainingRepository;

    public TrainingValidator(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public void checkTrainingCreate(TrainingCreateCommand command){
        UUID.fromString(command.getId());

        if(command.getStart() == null){
            throw  new IllegalArgumentException();
        }

        if(command.getFinish() == null){
            throw  new IllegalArgumentException();
        }

        if(command.getStart().isAfter(command.getFinish()) || command.getStart().equals(command.getFinish())){
            throw new IllegalArgumentException();
        }

        if(trainingRepository.existSomeTrainingInThisPeriod(command.getStart(), command.getFinish())){
            throw new IllegalArgumentException();
        }

    }

}
