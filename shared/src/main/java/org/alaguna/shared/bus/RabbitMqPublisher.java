package org.alaguna.shared.bus;

import org.alaguna.shared.utils.Constants;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public final class RabbitMqPublisher {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMqPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(DomainEvent domainEvent) throws AmqpException {
        String serializedDomainEvent = DomainEventJsonSerializer.serialize(domainEvent);

        Message message = new Message(
            serializedDomainEvent.getBytes(),
            MessagePropertiesBuilder.newInstance()
                .setContentEncoding("utf-8")
                .setContentType("application/json")
                .build()
        );

        rabbitTemplate.send(Constants.DOMAIN_EVENTS,
            Constants.DOMAIN_EVENTS_WITH_DOT + domainEvent.eventName(),
            message);
    }

    public void publish(Message domainEvent, String exchangeName, String routingKey) throws AmqpException {
        rabbitTemplate.send(exchangeName, routingKey, domainEvent);
    }
}
