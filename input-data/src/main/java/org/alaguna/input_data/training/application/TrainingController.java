package org.alaguna.input_data.training.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class TrainingController {

    private TrainingCreator trainerCreator;

    public TrainingController(TrainingCreator trainerCreator) {
        this.trainerCreator = trainerCreator;
    }

    @PostMapping("/trainings")
    public ResponseEntity<Object> createTraining(@RequestBody TrainingCreateCommand command){
        trainerCreator.createTraining(command);
        return ResponseEntity.noContent().build();
    }
}
