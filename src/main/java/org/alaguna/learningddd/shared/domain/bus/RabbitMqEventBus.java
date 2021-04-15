package org.alaguna.learningddd.shared.domain.bus;

import org.springframework.amqp.AmqpException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMqEventBus implements EventBus {
    private final RabbitMqPublisher publisher;
    private final String            exchangeName;

    public RabbitMqEventBus(RabbitMqPublisher publisher) {
        this.publisher         = publisher;
        this.exchangeName      = "domain_events";
    }

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent domainEvent) {
      //  try {
            this.publisher.publish(domainEvent, exchangeName);
       /* } catch (AmqpException error) {
            System.out.println("PUBLICADO NO");
            throw new IllegalArgumentException();
        }*/
    }
}
