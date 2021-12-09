package org.alaguna.input_data.training.application;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TrainingCreateCommand {

    private final String id;
    private final LocalDateTime start;
    private final LocalDateTime finish;

    public TrainingCreateCommand(String id, LocalDateTime start, LocalDateTime finish){
        this.id = id;
        this.start = start;
        this.finish = finish;
    }


}
