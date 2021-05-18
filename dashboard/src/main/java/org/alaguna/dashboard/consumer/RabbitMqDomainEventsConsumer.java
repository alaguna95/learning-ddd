package org.alaguna.dashboard.consumer;

import org.alaguna.dashboard.RabbitMqPublisher;
import org.alaguna.dashboard.consumer.Consumer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RabbitMqDomainEventsConsumer {

    private final static int MAX_RETRIES = 2;
    private final RabbitMqPublisher publisher;
    private final ConsumerWrapper consumerWrapper;

    @Autowired
    public RabbitMqDomainEventsConsumer(RabbitMqPublisher publisher,  ConsumerWrapper consumerWrapper
    ) {
        this.publisher = publisher;
        this.consumerWrapper = consumerWrapper;
    }

    @RabbitListener( autoStartup = "true", queues = "increment_training_on_training_created")
    public void consumer(Message message) {



        try{
            Consumer consumer = consumerWrapper.getConsumers().get(message.getMessageProperties().getConsumerQueue());
            consumer.on(null);


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
