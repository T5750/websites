package com.evangel.rocketmq.example.util;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;

public class Globals {
	public static final String DEFAULT_CHARSET = "UTF-8";
	public static final String PRODUCER_GROUP = "ExampleProducerGroup";
	public static final String CONSUMER_GROUP = "ExampleConsumerGroup";
	public static final String PULL_CONSUMER_GROUP = "ExamplePullConsumerGroup";
	public static final String TOPIC = "ExampleTopicTest";
	public static final String NAMESRV_ADDR = "127.0.0.1:9876";
	public static final String BROKER_NAME = "SC-201601191628";
	public static final String PRODUCER_GROUP_SQL = "ProducerGroupSql";
	public static final String CONSUMER_GROUP_SQL = "ConsumerGroupSql";
	public static final String TOPIC_SQL = "TopicSql";
	public static final String TOPIC_BENCHMARK = "TopicBenchmark";

	public static DefaultMQProducer setNamesrvAddr(DefaultMQProducer producer) {
		producer.setNamesrvAddr(NAMESRV_ADDR);
		return producer;
	}

	public static TransactionMQProducer setNamesrvAddr(
			TransactionMQProducer producer) {
		producer.setNamesrvAddr(NAMESRV_ADDR);
		return producer;
	}

	public static DefaultMQPushConsumer setNamesrvAddr(
			DefaultMQPushConsumer consumer) {
		consumer.setNamesrvAddr(NAMESRV_ADDR);
		return consumer;
	}

	public static DefaultMQPullConsumer setNamesrvAddr(
			DefaultMQPullConsumer consumer) {
		consumer.setNamesrvAddr(NAMESRV_ADDR);
		return consumer;
	}
}
