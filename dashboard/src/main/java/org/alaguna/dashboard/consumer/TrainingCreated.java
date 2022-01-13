package org.alaguna.dashboard.consumer;

import org.alaguna.shared.bus.Consumer;
import org.alaguna.shared.bus.RabbitMqPublisher;
import org.springframework.stereotype.Service;

@Service
public class TrainingCreated implements Consumer {

    private final RabbitMqPublisher publisher;

    public TrainingCreated(RabbitMqPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void on(String payload){
        System.out.println("OLA "+payload);
    }
}
