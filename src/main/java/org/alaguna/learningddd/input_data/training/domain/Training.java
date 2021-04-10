package org.alaguna.learningddd.input_data.training.domain;

public class Training {

    private TrainingId id;
    private TrainingPeriod period;

    public Training(TrainingId id, TrainingPeriod period){
        this.id = id;
        this.period = period;
    }

    public TrainingId getId() {
        return id;
    }

    public TrainingPeriod getPeriod() {
        return period;
    }
}
