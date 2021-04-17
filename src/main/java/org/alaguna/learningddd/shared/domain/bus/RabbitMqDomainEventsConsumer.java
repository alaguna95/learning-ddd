package org.alaguna.learningddd.shared.domain.bus;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RabbitMqDomainEventsConsumer {

    private final int  MAX_RETRIES = 2;
    private final RabbitMqPublisher publisher;

    public RabbitMqDomainEventsConsumer(RabbitMqPublisher publisher) {
        this.publisher = publisher;
    }

    @RabbitListener( autoStartup = "true", queues = "increment_training_on_training_created")
    public void consumer(Message message) throws Exception {

        try{
            System.out.println(message);
        } catch (Exception error) {
            String queue = message.getMessageProperties().getConsumerQueue();
            handleConsumptionError(message, queue);
        }
    }


    private void handleConsumptionError(Message message, String queue) {
        if (hasBeenRedeliveredTooMuch(message)) {
            sendToDeadLetter(message, queue);
        } else {
            sendToRetry(message, queue);
        }
    }

    private void sendToRetry(Message message, String queue) {
        sendMessageTo("domain_events", message, queue);
    }

    private void sendToDeadLetter(Message message, String queue) {
        sendMessageTo("dead_letter", message, queue);
    }

    private void sendMessageTo(String exchange, Message message, String queue) {
        Map<String, Object> headers = message.getMessageProperties().getHeaders();

        headers.put("redelivery_count", (int) headers.getOrDefault("redelivery_count", 0) + 1);

        MessageBuilder.fromMessage(message).andProperties(
                MessagePropertiesBuilder.newInstance()
                        .setContentEncoding("utf-8")
                        .setContentType("application/json")
                        .copyHeaders(headers)
                        .build());

        publisher.publish(message, exchange, queue);
    }

    private boolean hasBeenRedeliveredTooMuch(Message message) {
        return (int) message.getMessageProperties().getHeaders().getOrDefault("redelivery_count", 0) >= MAX_RETRIES;
    }



}
