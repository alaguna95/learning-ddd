package org.alaguna.learningddd.input_data.training.application;

import java.time.LocalDateTime;

public class CreateTrainingCommand {

    private final String id;
    private final LocalDateTime start;
    private final LocalDateTime end;

    public CreateTrainingCommand(String id, LocalDateTime start, LocalDateTime end){
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
