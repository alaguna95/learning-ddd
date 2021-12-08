package org.alaguna.dashboard.consumer;


import java.lang.annotation.*;
import org.alaguna.shared.bus.DomainEvent;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Consumer {
    Class<? extends DomainEvent>[] value();
}