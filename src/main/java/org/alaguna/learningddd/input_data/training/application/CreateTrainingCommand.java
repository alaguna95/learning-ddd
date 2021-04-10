package org.alaguna.learningddd.input_data.training.application;

import java.time.LocalDateTime;

public class CreateTrainingCommand {

    private final LocalDateTime start;
    private final LocalDateTime finish;

    public CreateTrainingCommand(LocalDateTime start, LocalDateTime finish){
        this.start = start;
        this.finish = finish;

    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }
}
