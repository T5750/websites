package com.evangel.rocketmq.example.openmessaging;

import io.openmessaging.*;
import io.openmessaging.rocketmq.domain.NonStandardKeys;

public class OMSPushConsumer {
	public static void main(String[] args) {
		final MessagingAccessPoint messagingAccessPoint = MessagingAccessPointFactory
				.getMessagingAccessPoint("openmessaging:rocketmq://IP1:9876,IP2:9876/namespace");
		final PushConsumer consumer = messagingAccessPoint
				.createPushConsumer(OMS.newKeyValue().put(
						NonStandardKeys.CONSUMER_GROUP, "OMS_CONSUMER"));
		messagingAccessPoint.startup();
		System.out.printf("MessagingAccessPoint startup OK%n");
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				consumer.shutdown();
				messagingAccessPoint.shutdown();
			}
		}));
		consumer.attachQueue("OMS_HELLO_TOPIC", new MessageListener() {
			@Override
			public void onMessage(final Message message,
					final ReceivedMessageContext context) {
				System.out.printf("Received one message: %s%n", message
						.headers().getString(MessageHeader.MESSAGE_ID));
				context.ack();
			}
		});
	}
}