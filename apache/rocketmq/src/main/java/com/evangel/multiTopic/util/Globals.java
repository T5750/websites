package com.evangel.multiTopic.util;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;

public class Globals {
	public static final String IP_ADDRESS = "127.0.0.1";
	public static final String MQ_ADDRESS = IP_ADDRESS + ":9876";
	public static final String DEFAULT_CHARSET = "UTF-8";
	public static final String MULTI_TOPIC = "MultiTopic";
	public static final String TOPIC_ONE = MULTI_TOPIC + 1;
	public static final String TOPIC_TWO = MULTI_TOPIC + 2;
	public static final String TOPIC_THREE = MULTI_TOPIC + 3;
	public static final String PRODUCER_GROUP = "ProducerGroup" + MULTI_TOPIC;
	public static final String CONSUMER_GROUP = "ConsumerGroup" + MULTI_TOPIC;
	public static final String PRODUCER_INSTANCE_NAME = "Producer"
			+ MULTI_TOPIC;
	public static final String CONSUMER_INSTANCE_NAME = "Consumer"
			+ MULTI_TOPIC;

	public static DefaultMQProducer setNamesrvAddr(DefaultMQProducer producer) {
		producer.setNamesrvAddr(MQ_ADDRESS);
		return producer;
	}

	public static DefaultMQPushConsumer setNamesrvAddr(
			DefaultMQPushConsumer consumer) {
		consumer.setNamesrvAddr(MQ_ADDRESS);
		return consumer;
	}
}
