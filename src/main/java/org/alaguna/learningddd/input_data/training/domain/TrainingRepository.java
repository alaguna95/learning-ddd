package org.alaguna.learningddd.input_data.training.domain;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

public interface TrainingRepository {

    void createTraining(Training training);

    boolean existSomeTrainingInThisPeriod(LocalDateTime start, LocalDateTime end);

    Training getTrainingById(String id);

}
