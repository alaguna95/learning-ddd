package org.alaguna.dashboard;


import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqEventBusConfiguration {

    @Bean
    public CachingConnectionFactory connection() {
        CachingConnectionFactory factory = new CachingConnectionFactory();

        factory.setHost("localhost");
        factory.setPort(5630);
        factory.setUsername("rabbitmq");
        factory.setPassword("2020");

        return factory;
    }

}