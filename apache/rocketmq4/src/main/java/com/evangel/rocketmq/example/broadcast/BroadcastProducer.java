package com.evangel.rocketmq.example.broadcast;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import com.evangel.rocketmq.example.util.ExampleUtil;

public class BroadcastProducer {
	public static void main(String[] args) throws Exception {
		DefaultMQProducer producer = new DefaultMQProducer(
				ExampleUtil.PRODUCER_GROUP);
		producer = ExampleUtil.setNamesrvAddr(producer);
		producer.start();
		for (int i = 0; i < 100; i++) {
			Message msg = new Message(ExampleUtil.TOPIC, "TagA", "OrderID188",
					"Hello world".getBytes(ExampleUtil.DEFAULT_CHARSET));
			SendResult sendResult = producer.send(msg);
			System.out.printf("%s%n", sendResult);
		}
		producer.shutdown();
	}
}