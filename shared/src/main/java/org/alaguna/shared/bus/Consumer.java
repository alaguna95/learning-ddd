package org.alaguna.shared.bus;

import org.springframework.amqp.core.Message;

public interface Consumer {

  void on(String payload, Message message);

}
