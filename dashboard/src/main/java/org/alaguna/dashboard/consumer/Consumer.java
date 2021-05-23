package org.alaguna.dashboard.consumer;

import org.alaguna.dashboard.shared.DomainEvent;

public interface Consumer {

    void on(DomainEvent domainEvent);
}
