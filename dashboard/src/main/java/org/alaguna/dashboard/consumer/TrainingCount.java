package org.alaguna.dashboard.consumer;

import org.alaguna.shared.bus.Consumer;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

@Service
public class TrainingCount implements Consumer {

  @Override
  public void on(String payload, Message message) {
    System.out.println("Count1 " + payload);
  }

}
