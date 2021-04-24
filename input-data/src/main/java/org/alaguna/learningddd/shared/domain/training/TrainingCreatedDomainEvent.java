package org.alaguna.learningddd.shared.domain.training;

import lombok.EqualsAndHashCode;
import org.alaguna.learningddd.shared.domain.bus.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

@EqualsAndHashCode(callSuper = false)
public class TrainingCreatedDomainEvent extends DomainEvent {

    public TrainingCreatedDomainEvent(String aggregateId) {
        super(aggregateId);
    }

    public TrainingCreatedDomainEvent(String aggregateId,String eventId, String occurredOn ) {
        super(aggregateId, eventId, occurredOn);
    }

    @Override
    public String eventName() {
        return "training_created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new TrainingCreatedDomainEvent(aggregateId, eventId, occurredOn);
    }
}
