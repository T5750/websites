package com.evangel.rocketmq.example.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import com.evangel.rocketmq.example.util.Globals;

public class OnewayProducer {
	public static void main(String[] args) throws Exception {
		// Instantiate with a producer group name.
		DefaultMQProducer producer = new DefaultMQProducer(
				Globals.PRODUCER_GROUP);
		// Launch the instance.
		producer = Globals.setNamesrvAddr(producer);
		producer.start();
		for (int i = 0; i < 100; i++) {
			// Create a message instance, specifying topic, tag and message
			// body.
			Message msg = new Message(Globals.TOPIC /* Topic */,
					"TagAOnewayProducer" /* Tag */,
					("Hello RocketMQ " + i).getBytes(Globals.DEFAULT_CHARSET) /*
																			 * Message
																			 * body
																			 */
			);
			// Call send message to deliver message to one of brokers.
			producer.sendOneway(msg);
		}
		// Shut down once the producer instance is not longer in use.
		producer.shutdown();
	}
}