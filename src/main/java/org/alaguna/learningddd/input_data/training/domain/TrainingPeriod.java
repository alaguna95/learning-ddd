package org.alaguna.learningddd.input_data.training.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class TrainingPeriod {

    private TrainingStart start;
    private TrainingFinish finish;

    public TrainingPeriod(TrainingStart start, TrainingFinish finish){
        this.start = start;
        this.finish = finish;


    }

    public TrainingStart getStart() {
        return start;
    }

    public TrainingFinish getFinish() {
        return finish;
    }
}
