package com.evangel.rocketmq.example.openmessaging;

import io.openmessaging.*;
import io.openmessaging.rocketmq.domain.NonStandardKeys;

public class OMSPullConsumer {
	public static void main(String[] args) {
		final MessagingAccessPoint messagingAccessPoint = MessagingAccessPointFactory
				.getMessagingAccessPoint("openmessaging:rocketmq://IP1:9876,IP2:9876/namespace");
		final PullConsumer consumer = messagingAccessPoint.createPullConsumer(
				"OMS_HELLO_TOPIC",
				OMS.newKeyValue().put(NonStandardKeys.CONSUMER_GROUP,
						"OMS_CONSUMER"));
		messagingAccessPoint.startup();
		System.out.printf("MessagingAccessPoint startup OK%n");
		consumer.startup();
		System.out.printf("Consumer startup OK%n");
		Message message = consumer.poll();
		if (message != null) {
			String msgId = message.headers()
					.getString(MessageHeader.MESSAGE_ID);
			System.out.printf("Received one message: %s%n", msgId);
			consumer.ack(msgId);
		}
		consumer.shutdown();
		messagingAccessPoint.shutdown();
	}
}