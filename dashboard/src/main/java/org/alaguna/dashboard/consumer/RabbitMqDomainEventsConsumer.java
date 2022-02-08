package org.alaguna.dashboard.consumer;

import co.elastic.apm.api.ElasticApm;
import co.elastic.apm.api.Scope;
import co.elastic.apm.api.Transaction;
import java.lang.reflect.InvocationTargetException;
import lombok.extern.slf4j.Slf4j;
import org.alaguna.shared.bus.RabbitMqPublisher;
import org.alaguna.shared.utils.Constants;
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
@Slf4j
public class RabbitMqDomainEventsConsumer {

    private final int MAX_RETRIES = 2;
    private final String REDELIVERY_COUNT = "redelivery_count";
    private final String METHOD_NAME = "on";

    private final RabbitMqPublisher publisher;
    private final ApplicationContext context;


    @Autowired
    public RabbitMqDomainEventsConsumer(RabbitMqPublisher publisher, ApplicationContext context
    ) {
        this.publisher = publisher;
        this.context = context;
    }

    @RabbitListener( autoStartup = "true", queues = Constants.DOMAIN_EVENTS)
    public void consumer(@Payload String payload, Message message) {

        String routingKey = message.getMessageProperties().getReceivedRoutingKey();

        try{
            String consumerName = getConsumerName(routingKey);
            Object consumer = findConsumer(consumerName);
            Method subscriberOnMethod = consumer.getClass().getMethod(METHOD_NAME, String.class, Message.class);

            Transaction transaction = ElasticApm.startTransactionWithRemoteParent(key -> message.getMessageProperties()
                .getHeader("traceparent"));
            try(final Scope scope = transaction.activate()) {
                transaction.setName(consumerName);
                transaction.setType("messaging");
                subscriberOnMethod.invoke(consumer, payload, message);
            } catch (InvocationTargetException ite){
                transaction.captureException(ite.getCause());
                //log.error("[consumer] InvocationTargetException: ", ite.getCause());
                handleConsumptionError(message, routingKey);
            } catch (Exception e) {
                transaction.captureException(e);
                //log.error("[consumer] Exception: ", e);
                handleConsumptionError(message, routingKey);
            } finally {
                transaction.end();
            }

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
        sendMessageTo(Constants.DOMAIN_EVENTS, message, routingKey);
    }

    private void sendToDeadLetter(Message message, String routingKey) {
        String routingKeyDeadLetter = routingKey.replace(Constants.DOMAIN_EVENTS, Constants.DEAD_LETTER);
        sendMessageTo(Constants.DEAD_LETTER, message, routingKeyDeadLetter);
    }

    private void sendMessageTo(String exchange, Message message, String routingKey) {
        Map<String, Object> headers = message.getMessageProperties().getHeaders();

        headers.put(REDELIVERY_COUNT, (int) headers.getOrDefault(REDELIVERY_COUNT, 0) + 1);

        MessageBuilder.fromMessage(message).andProperties(
                MessagePropertiesBuilder.newInstance()
                        .setContentEncoding("utf-8")
                        .setContentType("application/json")
                        .copyHeaders(headers)
                        .build());

        publisher.publish(message, exchange, routingKey);
    }

    private boolean hasBeenRedeliveredTooMuch(Message message) {
        return (int) message.getMessageProperties().getHeaders().getOrDefault(REDELIVERY_COUNT, 0) >= MAX_RETRIES;
    }

    private String getConsumerName(String routingKey){
        return Utils.toCamelFirstLower(routingKey.replace(Constants.DOMAIN_EVENTS_WITH_DOT,""));
    }

    private Object findConsumer(String consumerName){
        return context.getBean(consumerName);
    }

}
