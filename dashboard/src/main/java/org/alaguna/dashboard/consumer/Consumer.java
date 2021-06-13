package org.alaguna.dashboard.consumer;

import org.alaguna.dashboard.shared.DomainEvent;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Consumer {
    Class<? extends DomainEvent>[] value();
}