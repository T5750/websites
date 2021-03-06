package com.evangel.rocketmq.example.schedule;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import com.evangel.rocketmq.example.util.Globals;

public class ScheduledMessageConsumer {
	public static void main(String[] args) throws Exception {
		// Instantiate message consumer
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
				Globals.CONSUMER_GROUP);
		// Subscribe topics
		consumer = Globals.setNamesrvAddr(consumer);
		consumer.subscribe(Globals.TOPIC, "*");
		// Register message listener
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(
					List<MessageExt> messages,
					ConsumeConcurrentlyContext context) {
				for (MessageExt message : messages) {
					// Print approximate delay time period
					System.out.println("Receive message[msgId="
							+ message.getMsgId()
							+ "] "
							+ (System.currentTimeMillis() - message
									.getStoreTimestamp()) + "ms later");
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		// Launch consumer
		consumer.start();
	}
}