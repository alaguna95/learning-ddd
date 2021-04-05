package org.alaguna.learningddd.input_data.training.domain;

public class TrainingPeriod {

    private TrainingStart start;
    private TrainingEnd end;

    public TrainingPeriod(TrainingStart start, TrainingEnd end){

        if(start.value().isAfter(end.value()) || start.equals(end)){
            throw new IllegalArgumentException();
        }

        this.start = start;
        this.end = end;


    }

}
