package org.alaguna.dashboard.consumer;

import org.alaguna.dashboard.RabbitMqPublisher;
import org.alaguna.dashboard.shared.DomainEvent;
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
