package org.alaguna.shared.bus;

import java.util.List;
import org.springframework.amqp.AmqpException;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqEventBus implements EventBus {
    private final RabbitMqPublisher publisher;

    public RabbitMqEventBus(RabbitMqPublisher publisher) {
        this.publisher         = publisher;
    }

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent domainEvent) {
      try {
            this.publisher.publish(domainEvent);
       } catch (AmqpException error) {
            throw new IllegalArgumentException();
        }
    }
}
