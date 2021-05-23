package org.alaguna.dashboard;

import org.alaguna.dashboard.consumer.Consumer;
import org.alaguna.dashboard.shared.DomainEvent;
import org.springframework.stereotype.Service;

@Service
public class IncrementTrainingCountOnTrainingCreated implements Consumer {

    public void on(DomainEvent domainEvent){
        System.out.println("OLA");
    }
}
