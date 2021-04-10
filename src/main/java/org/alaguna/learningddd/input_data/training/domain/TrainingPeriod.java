package org.alaguna.learningddd.input_data.training.domain;

public class TrainingPeriod {

    private TrainingStart start;
    private TrainingFinish finish;

    public TrainingPeriod(TrainingStart start, TrainingFinish finish){

        if(start.value().isAfter(finish.value()) || start.value().equals(finish.value())){
            throw new IllegalArgumentException();
        }

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
