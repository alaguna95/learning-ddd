package org.alaguna.shared.bus;

import java.util.List;

public interface EventBus {
    void publish(final List<DomainEvent> events);
}
