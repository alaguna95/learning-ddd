package org.alaguna.shared.bus;

import org.alaguna.shared.utils.Utils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Map;

@Service
public class RabbitMqDomainEventsConsumer {

    private final int MAX_RETRIES = 2;
    private final String DOMAIN_EVENTS="domain_events";
    private final String DOMAIN_EVENTS_WITH_DOT=DOMAIN_EVENTS+".";
    private final String DEAD_LETTER="dead_letter";


    private final RabbitMqPublisher publisher;
    private final ApplicationContext context;



    @Autowired
    public RabbitMqDomainEventsConsumer(RabbitMqPublisher publisher, ApplicationContext context
    ) {
        this.publisher = publisher;
        this.context = context;
    }

    @RabbitListener( autoStartup = "true", queues = DOMAIN_EVENTS)
    public void consumer(@Payload String payload, Message message) {

        String routingKey = message.getMessageProperties().getReceivedRoutingKey();

        try{
            Object subscriber = findConsumer(routingKey);
            Method subscriberOnMethod = subscriber.getClass().getMethod("on" , String.class);
            subscriberOnMethod.invoke(subscriber, payload);

        } catch (Exception error) {
            handleConsumptionError(message, routingKey);
        }
    }


    private void handleConsumptionError(Message message, String routingKey) {
        if (hasBeenRedeliveredTooMuch(message)) {
            sendToDeadLetter(message, routingKey);
        } else {
            sendToRetry(message, routingKey);
        }
    }

    private void sendToRetry(Message message, String routingKey) {
        sendMessageTo(DOMAIN_EVENTS, message, routingKey);
    }

    private void sendToDeadLetter(Message message, String routingKey) {
        sendMessageTo("dead_letter", message, routingKey);
    }

    private void sendMessageTo(String exchange, Message message, String routingKey) {
        Map<String, Object> headers = message.getMessageProperties().getHeaders();

        headers.put(DEAD_LETTER, (int) headers.getOrDefault("redelivery_count", 0) + 1);

        MessageBuilder.fromMessage(message).andProperties(
                MessagePropertiesBuilder.newInstance()
                        .setContentEncoding("utf-8")
                        .setContentType("application/json")
                        .copyHeaders(headers)
                        .build());

        publisher.publish(message, exchange, DEAD_LETTER+ "1");
    }

    private boolean hasBeenRedeliveredTooMuch(Message message) {
        return (int) message.getMessageProperties().getHeaders().getOrDefault("redelivery_count", 0) >= MAX_RETRIES;
    }

    private Object findConsumer(String routingKey){
        String subscriberName = Utils.toCamelFirstLower(routingKey.replace(DOMAIN_EVENTS_WITH_DOT,""));
        return context.getBean(subscriberName);
    }

}
