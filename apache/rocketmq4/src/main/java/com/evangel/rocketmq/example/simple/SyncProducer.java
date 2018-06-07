package com.evangel.rocketmq.example.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import com.evangel.rocketmq.example.util.ExampleUtil;

public class SyncProducer {
	public static void main(String[] args) throws Exception {
		// Instantiate with a producer group name.
		DefaultMQProducer producer = new DefaultMQProducer(
				ExampleUtil.PRODUCER_GROUP);
		// Launch the instance.
		producer = ExampleUtil.setNamesrvAddr(producer);
		producer.start();
		for (int i = 0; i < 100; i++) {
			// Create a message instance, specifying topic, tag and message
			// body.
			Message msg = new Message(ExampleUtil.TOPIC /* Topic */,
					"TagASyncProducer" /* Tag */,
					("Hello RocketMQ " + i)
							.getBytes(ExampleUtil.DEFAULT_CHARSET) /*
																	 * Message
																	 * body
																	 */
			);
			// Call send message to deliver message to one of brokers.
			SendResult sendResult = producer.send(msg);
			System.out.printf("%s%n", sendResult);
		}
		// Shut down once the producer instance is not longer in use.
		producer.shutdown();
	}
}