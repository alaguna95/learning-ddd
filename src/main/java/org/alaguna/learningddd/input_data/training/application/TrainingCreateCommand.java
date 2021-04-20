package org.alaguna.learningddd.input_data.training.application;

import java.time.LocalDateTime;
import java.util.UUID;

public class TrainingCreateCommand {

    private final String id;
    private final LocalDateTime start;
    private final LocalDateTime finish;

    public TrainingCreateCommand(String id, LocalDateTime start, LocalDateTime finish){
        this.id = id;
        this.start = start;
        this.finish = finish;

        checkIsValid();
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    private void checkIsValid(){
        UUID.fromString(id);

        if(start == null){
            throw  new IllegalArgumentException();
        }

        if(finish == null){
            throw  new IllegalArgumentException();
        }

        if(start.isAfter(finish) || start.equals(finish)){
            throw new IllegalArgumentException();
        }


    }
}
