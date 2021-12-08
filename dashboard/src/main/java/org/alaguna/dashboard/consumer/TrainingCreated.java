package org.alaguna.dashboard.consumer;

import org.alaguna.shared.bus.DomainEvent;
import org.alaguna.shared.bus.RabbitMqPublisher;
import org.springframework.stereotype.Service;

@Service
@Consumer({DomainEvent.class})
public class TrainingCreated {

    private final RabbitMqPublisher publisher;

    public TrainingCreated(RabbitMqPublisher publisher) {
        this.publisher = publisher;
    }

    public void on(String payload){
        System.out.println("OLA "+payload);
    }
}
