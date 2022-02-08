package org.alaguna.dashboard.consumer;

import org.alaguna.shared.bus.Consumer;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

@Service
public class TrainingCount2 implements Consumer {

  @Override
  public void on(String payload, Message message) {

    System.out.println("COunt2 " + payload);
    int fail = 3/0;
  }
}
