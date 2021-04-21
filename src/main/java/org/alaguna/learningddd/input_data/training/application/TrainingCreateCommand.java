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


}
