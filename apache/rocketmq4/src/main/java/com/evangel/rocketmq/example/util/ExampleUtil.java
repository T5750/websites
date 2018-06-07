package com.evangel.rocketmq.example.util;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

public class ExampleUtil {
	public static final String DEFAULT_CHARSET = "UTF-8";
	public static final String PRODUCER_GROUP = "ExampleProducerGroup";
	public static final String CONSUMER_GROUP = "ExampleConsumerGroup";
	public static final String TOPIC = "ExampleTopicTest";
	public static final String NAMESRV_ADDR = "127.0.0.1:9876";

	public static DefaultMQProducer setNamesrvAddr(DefaultMQProducer producer) {
		producer.setNamesrvAddr(NAMESRV_ADDR);
		return producer;
	}

	public static DefaultMQPushConsumer setNamesrvAddr(
			DefaultMQPushConsumer consumer) {
		consumer.setNamesrvAddr(NAMESRV_ADDR);
		return consumer;
	}
}
