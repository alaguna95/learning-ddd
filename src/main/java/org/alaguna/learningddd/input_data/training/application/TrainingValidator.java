package org.alaguna.learningddd.input_data.training.application;

import org.alaguna.learningddd.input_data.training.domain.TrainingRepository;
import org.springframework.stereotype.Service;

@Service
public class TrainingValidator {

    private TrainingRepository trainingRepository;

    public TrainingValidator(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public void checkTrainingCreate(TrainingCreateCommand trainingCommand){




    }

}
