package org.alaguna.dashboard.consumer;

import org.alaguna.dashboard.RabbitMqPublisher;
import org.alaguna.dashboard.Utils;
import org.alaguna.dashboard.shared.DomainEvent;
import org.reflections.Reflections;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

@Service
public class RabbitMqDomainEventsConsumer {

    private final static int MAX_RETRIES = 2;
    private final RabbitMqPublisher publisher;
    private final ApplicationContext context;

    @Autowired
    public RabbitMqDomainEventsConsumer(RabbitMqPublisher publisher, ApplicationContext context
    ) {
        this.publisher = publisher;
        this.context = context;
    }

    @RabbitListener( autoStartup = "true", queues = "increment_training_count_on_training_created")
    public void consumer(Message message) {

        String queue = message.getMessageProperties().getConsumerQueue();

        try{
            Object subscriber = findConsumer(queue);

            Method subscriberOnMethod = subscriber.getClass().getMethod("on" , DomainEvent.class);

            Object[] arguments = new Object[1];
            arguments[0] = null;

            subscriberOnMethod.invoke(subscriber, arguments);

        } catch (Exception error) {
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


    private Object findConsumer(String queue){
        String[] queueParts     = queue.split("\\.");
        String   subscriberName = Utils.toCamelFirstLower(queueParts[queueParts.length - 1]);
        return context.getBean(subscriberName);
    }





}
