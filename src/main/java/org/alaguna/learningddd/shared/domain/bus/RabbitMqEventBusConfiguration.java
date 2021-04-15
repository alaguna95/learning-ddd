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

        factory.setHost("localhost");
        factory.setPort(5630);
        factory.setUsername("rabbitmq");
        factory.setPassword("2020");

        return factory;
    }

    @Bean
    public Declarables declaration() {

        String exchangeName = "domain_events";

        TopicExchange    domainEventsExchange           = new TopicExchange(exchangeName, true, false);
        List<Declarable> declarables                    = new ArrayList<>();
        declarables.add(domainEventsExchange);

        String queueName = "increment_training_on_training_created";

        Queue queue = QueueBuilder.durable(queueName).build();

        Binding fromExchangeSameQueueBinding = BindingBuilder
                .bind(queue)
                .to(domainEventsExchange)
                .with("training_created");



        List<Declarable> queuesAndBindings = new ArrayList<>();
        queuesAndBindings.add(queue);
        queuesAndBindings.add(fromExchangeSameQueueBinding);

        declarables.addAll(queuesAndBindings);

        return new Declarables(declarables);
    }


}