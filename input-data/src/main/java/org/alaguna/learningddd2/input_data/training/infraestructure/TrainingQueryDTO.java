package org.alaguna.learningddd2.input_data.training.infraestructure;

import java.time.LocalDateTime;

public class TrainingQueryDTO {

    private String id;
    private LocalDateTime start;
    private LocalDateTime finish;

    public TrainingQueryDTO(String id, LocalDateTime start, LocalDateTime finish) {
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

