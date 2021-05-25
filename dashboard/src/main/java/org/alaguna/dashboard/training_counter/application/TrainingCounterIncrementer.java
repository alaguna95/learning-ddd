package org.alaguna.dashboard.training_counter.application;

import org.alaguna.dashboard.training_counter.domain.TrainingCounterRepository;
import org.springframework.stereotype.Service;

@Service
public class TrainingCounterIncrementer {

    private TrainingCounterRepository trainingCounterRepository;


    public TrainingCounterIncrementer(TrainingCounterRepository trainingCounterRepository) {
        this.trainingCounterRepository = trainingCounterRepository;
    }



}
