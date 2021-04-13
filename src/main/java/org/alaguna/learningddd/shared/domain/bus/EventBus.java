package org.alaguna.learningddd.shared.domain.bus;

import java.util.List;

public interface EventBus {
    void publish(final List<DomainEvent> events);
}
