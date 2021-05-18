package org.alaguna.dashboard.consumer;

import java.util.Map;

public class ConsumerWrapper {

    public ConsumerWrapper(Map<String, Consumer> consumers){
        this.consumers = consumers;
    }

    private Map<String, Consumer> consumers;

    public Map<String, Consumer> getConsumers() {
        return consumers;
    }

}
