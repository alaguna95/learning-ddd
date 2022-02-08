package org.alaguna.dashboard.consumer;

import lombok.RequiredArgsConstructor;
import org.alaguna.shared.bus.Consumer;
import org.alaguna.shared.bus.EventBus;
import org.alaguna.shared.bus.RabbitMqPublisher;
import org.alaguna.shared.utils.Constants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainingCreated implements Consumer {

    private final RabbitMqPublisher publisher;
    private final EventBus eventBus;

    @Override
    public void on(String payload, Message message){
        MessageBuilder.fromMessage(message).andProperties(
            MessagePropertiesBuilder.newInstance()
                .setContentEncoding("utf-8")
                .setContentType("application/json")
                .build());

        publisher.publish(message, Constants.DOMAIN_EVENTS, Constants.DOMAIN_EVENTS+".training_count");
        publisher.publish(message, Constants.DOMAIN_EVENTS, Constants.DOMAIN_EVENTS+".training_count2");}
}
