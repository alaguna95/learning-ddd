package org.alaguna.dashboard.consumer;

import lombok.Getter;

import java.util.Map;

@Getter
public class ConsumerWrapper {

    public ConsumerWrapper(Map<String, Consumer> consumers){
        this.consumers = consumers;
    }

    private Map<String, Consumer> consumers;

}
