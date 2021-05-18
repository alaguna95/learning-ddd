package org.alaguna.dashboard.consumer;

import org.alaguna.dashboard.DomainEvent;

public interface Consumer {

    void on(DomainEvent domainEvent);
}
