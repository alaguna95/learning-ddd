package org.alaguna.learningddd.shared.domain.bus;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqDomainEventsConsumer {

    private final String                      CONSUMER_NAME          = "domain_events_consumer";

    @RabbitListener(id = CONSUMER_NAME, autoStartup = "true", queues = "increment_training_on_training_created")
    public void consumer(Message message) throws Exception {
        System.out.println(message);
    }
}
