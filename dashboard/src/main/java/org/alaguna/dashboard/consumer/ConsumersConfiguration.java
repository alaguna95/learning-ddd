package org.alaguna.dashboard.consumer;

import org.alaguna.dashboard.IncrementTrainingCountOnTrainingCreated;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumersConfiguration {

    @Bean
    public ConsumerWrapper consumerWrapper(){
        Map<String, Consumer> consumers = new HashMap<>();
        consumers.put("increment_training_on_training_created", new IncrementTrainingCountOnTrainingCreated());
        return new ConsumerWrapper(consumers);
    }
}
