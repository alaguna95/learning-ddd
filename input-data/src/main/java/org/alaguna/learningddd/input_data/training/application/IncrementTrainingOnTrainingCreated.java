package org.alaguna.learningddd.input_data.training.application;

import org.alaguna.learningddd.shared.domain.bus.DomainEventSubscriber;
import org.alaguna.learningddd.shared.domain.training.TrainingCreatedDomainEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@DomainEventSubscriber({TrainingCreatedDomainEvent.class})
public class IncrementTrainingOnTrainingCreated {

    @EventListener
    public void on(TrainingCreatedDomainEvent event) {
        System.out.println("Ola");
    }
}
