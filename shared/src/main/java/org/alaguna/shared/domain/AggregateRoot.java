package org.alaguna.shared.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.alaguna.shared.bus.DomainEvent;

public abstract class AggregateRoot {
    private List<DomainEvent> domainEvents = new ArrayList<>();

    final public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = domainEvents;

        domainEvents = Collections.emptyList();

        return events;
    }

    final protected void record(DomainEvent event) {
        domainEvents.add(event);
    }
}

