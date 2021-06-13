package org.alaguna.dashboard.consumer;

import org.alaguna.dashboard.consumer.Consumer;
import org.alaguna.dashboard.shared.DomainEvent;
import org.springframework.stereotype.Service;

@Service
@Consumer({DomainEvent.class})
public class IncrementTrainingCountOnTrainingCreated  {

    public void on(DomainEvent domainEvent){
        System.out.println("OLA");
    }
}
