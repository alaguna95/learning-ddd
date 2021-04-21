package org.alaguna.learningddd.objects;

import org.alaguna.learningddd.input_data.training.application.TrainingCreateCommand;

import java.time.LocalDateTime;

public class TrainingCreateCommandBuilder {

    private String id;
    private LocalDateTime start;
    private LocalDateTime end;

    public TrainingCreateCommandBuilder(){
        id = "86dd324c-2046-4dfb-a89d-4ed069cf8357";
        start = LocalDateTime.of(2020, 1, 1, 17, 30);
        end = LocalDateTime.of(2020, 1, 1, 18, 00);

    }

    public TrainingCreateCommandBuilder withId(String id){
        this.id=id;
        return this;
    }
    public TrainingCreateCommandBuilder withStart(LocalDateTime start){
        this.start=start;
        return this;
    }

    public TrainingCreateCommandBuilder withEnd(LocalDateTime end){
        this.end=end;
        return this;
    }

    public TrainingCreateCommand build(){
        return new TrainingCreateCommand(id, start, end);
    }

}
