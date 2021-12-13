package org.alaguna.input_data.training.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class TrainingController {

    private TrainingCreate trainerCreate;

    public TrainingController(TrainingCreate trainerCreate) {
        this.trainerCreate = trainerCreate;
    }

    @PostMapping("/trainings")
    public ResponseEntity<Object> createTraining(@RequestBody TrainingCreateCommand command){
        trainerCreate.createTraining(command);
        return ResponseEntity.noContent().build();
    }
}
