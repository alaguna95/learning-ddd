package org.alaguna.shared.bus;


import org.alaguna.shared.utils.Constants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RabbitMqEventBusConfiguration {

    @Value( "${rabbit.host}" )
    private String rabbitHost;

    @Value( "${rabbit.port}" )
    private Integer rabbitPort;

    @Value( "${rabbit.username}" )
    private String rabbitUsername;

    @Value( "${rabbit.password}" )
    private String rabbitPassword;


    @Bean
    public CachingConnectionFactory connection()  {
        CachingConnectionFactory factory = new CachingConnectionFactory();

        factory.setHost(rabbitHost);
        factory.setPort(rabbitPort);
        factory.setUsername(rabbitUsername);
        factory.setPassword(rabbitPassword);

        return factory;
    }

    @Bean
    public Declarables declaration() {

        TopicExchange domainEventsExchange = new TopicExchange(Constants.DOMAIN_EVENTS, true, false);
        TopicExchange deadLetterExchange = new TopicExchange(Constants.DEAD_LETTER, true, false);

        List<Declarable> declarables = new ArrayList<>();
        declarables.add(domainEventsExchange);
        declarables.add(deadLetterExchange);


        declarables.addAll(createQueueDomainEvents(domainEventsExchange));
        declarables.addAll(createQueueDeadLetter(deadLetterExchange));

        return new Declarables(declarables);
    }


    private List<Declarable> createQueueDomainEvents(TopicExchange domainEventsExchange ){

        List<Declarable> queueAndBinding = new ArrayList<>();

        Queue queue = QueueBuilder.durable(Constants.DOMAIN_EVENTS).build();

        Binding fromExchangeSameQueueBinding = BindingBuilder
                .bind(queue)
                .to(domainEventsExchange)
                .with(Constants.DOMAIN_EVENTS_WITH_ASTERISK);

        queueAndBinding.add(queue);
        queueAndBinding.add(fromExchangeSameQueueBinding);

        return queueAndBinding;
    }

    private List<Declarable> createQueueDeadLetter(TopicExchange deadLetterExchange){

        List<Declarable> queueAndBinding = new ArrayList<>();

        Queue deadLetterQueue = QueueBuilder.durable(Constants.DEAD_LETTER).build();

        Binding fromDeadLetterExchangeSameQueueBinding = BindingBuilder
                .bind(deadLetterQueue)
                .to(deadLetterExchange)
                .with(Constants.DEAD_LETTER_WITH_ASTERISK);

        queueAndBinding.add(deadLetterQueue);
        queueAndBinding.add(fromDeadLetterExchangeSameQueueBinding);

        return queueAndBinding;
    }
}