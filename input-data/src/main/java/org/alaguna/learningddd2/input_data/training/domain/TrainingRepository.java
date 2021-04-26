package org.alaguna.learningddd2.input_data.training.domain;

import java.time.LocalDateTime;

public interface TrainingRepository {

    void createTraining(Training training);

    boolean existSomeTrainingInThisPeriod(LocalDateTime start, LocalDateTime end);

    Training getTrainingById(String id);

}
