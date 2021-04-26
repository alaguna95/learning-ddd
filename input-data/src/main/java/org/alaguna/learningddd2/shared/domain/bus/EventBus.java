package org.alaguna.learningddd2.shared.domain.bus;

import java.util.List;

public interface EventBus {
    void publish(final List<DomainEvent> events);
}
