package com.evangel.rocketmq.example.schedule;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import com.evangel.rocketmq.example.util.Globals;

public class ScheduledMessageProducer {
	public static void main(String[] args) throws Exception {
		// Instantiate a producer to send scheduled messages
		DefaultMQProducer producer = new DefaultMQProducer(
				Globals.PRODUCER_GROUP);
		// Launch producer
		producer = Globals.setNamesrvAddr(producer);
		producer.start();
		int totalMessagesToSend = 100;
		for (int i = 0; i < totalMessagesToSend; i++) {
			Message message = new Message(Globals.TOPIC,
					("Hello scheduled message " + i).getBytes());
			// This message will be delivered to consumer 10 seconds later.
			message.setDelayTimeLevel(3);
			// Send the message
			producer.send(message);
		}
		// Shutdown producer after use.
		producer.shutdown();
	}
}