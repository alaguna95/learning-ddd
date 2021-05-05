package org.alaguna.learningddd.shared.domain.bus;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RabbitMqEventBusConfiguration {

    @Bean
    public CachingConnectionFactory connection()  {
        CachingConnectionFactory factory = new CachingConnectionFactory();

        factory.setHost("rabbitmq");
        factory.setPort(5672);
        factory.setUsername("rabbitmq");
        factory.setPassword("2020");

        return factory;
    }

    @Bean
    public Declarables declaration() {


        TopicExchange domainEventsExchange = new TopicExchange("domain_events", true, false);
        TopicExchange deadLetterExchange = new TopicExchange("dead_letter", true, false);

        List<Declarable> declarables = new ArrayList<>();
        declarables.add(domainEventsExchange);
        declarables.add(deadLetterExchange);


        declarables.addAll(createQueueDomainEvents(domainEventsExchange));
        declarables.addAll(createQueueDeadLetter(deadLetterExchange));

        return new Declarables(declarables);
    }


    private List<Declarable> createQueueDomainEvents(TopicExchange domainEventsExchange ){
        String queueName = "increment_training_on_training_created";


        List<Declarable> queueAndBinding = new ArrayList<>();

        Queue queue = QueueBuilder.durable(queueName).build();

        Binding fromExchangeSameQueueBinding = BindingBuilder
                .bind(queue)
                .to(domainEventsExchange)
                .with("training_created");

        Binding fromExchangeSameQueueBinding2 = BindingBuilder
                .bind(queue)
                .to(domainEventsExchange)
                .with("increment_training_on_training_created");

        queueAndBinding.add(queue);
        queueAndBinding.add(fromExchangeSameQueueBinding);
        queueAndBinding.add(fromExchangeSameQueueBinding2);

        return queueAndBinding;
    }

    private List<Declarable> createQueueDeadLetter(TopicExchange deadLetterExchange){


        List<Declarable> queueAndBinding = new ArrayList<>();

        String deadLetterQueueName = "dead_letter.increment_training_on_training_created";


        Queue deadLetterQueue = QueueBuilder.durable(deadLetterQueueName).build();

        Binding fromDeadLetterExchangeSameQueueBinding = BindingBuilder
                .bind(deadLetterQueue)
                .to(deadLetterExchange)
                .with("increment_training_on_training_created");

        queueAndBinding.add(deadLetterQueue);
        queueAndBinding.add(fromDeadLetterExchangeSameQueueBinding);


        return queueAndBinding;
    }
}