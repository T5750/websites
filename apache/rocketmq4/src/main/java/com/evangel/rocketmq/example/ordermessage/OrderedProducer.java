package com.evangel.rocketmq.example.ordermessage;

import java.util.List;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import com.evangel.rocketmq.example.util.ExampleUtil;

public class OrderedProducer {
	public static void main(String[] args) throws Exception {
		// Instantiate with a producer group name.
		DefaultMQProducer producer = new DefaultMQProducer(
				ExampleUtil.PRODUCER_GROUP);
		// Launch the instance.
		producer = ExampleUtil.setNamesrvAddr(producer);
		producer.start();
		String[] tags = new String[] { "TagA", "TagB", "TagC", "TagD", "TagE" };
		for (int i = 0; i < 100; i++) {
			int orderId = i % 10;
			// Create a message instance, specifying topic, tag and message
			// body.
			Message msg = new Message("TopicTestOrderedProducer", tags[i
					% tags.length], "KEY" + i,
					("Hello RocketMQ " + i)
							.getBytes(ExampleUtil.DEFAULT_CHARSET));
			SendResult sendResult = producer.send(msg,
					new MessageQueueSelector() {
						@Override
						public MessageQueue select(List<MessageQueue> mqs,
								Message msg, Object arg) {
							Integer id = (Integer) arg;
							int index = id % mqs.size();
							return mqs.get(index);
						}
					}, orderId);
			System.out.printf("%s%n", sendResult);
		}
		// server shutdown
		producer.shutdown();
	}
}