package com.evangel.util;

import org.apache.rocketmq.client.producer.DefaultMQProducer;

public class Globals {
	public static final String IP_ADDRESS = "127.0.0.1";
	public static final String MQ_ADDRESS = IP_ADDRESS + ":9876";
	public static final String DEFAULT_CHARSET = "UTF-8";
	public static final String MQ_ENTITY = "Balance";
	public static final String TOPIC = "Topic" + MQ_ENTITY;
	public static final String PRODUCER_GROUP = "ProducerGroup" + MQ_ENTITY;
	public static final String TAGS = "Tag" + MQ_ENTITY;
	public static final String KEYS = "Key" + MQ_ENTITY;
	/**
	 * for rocketmq-transaction-pay
	 */
	public static final String MQ_ENTITY_PAY = "Pay";
	public static final String TOPIC_PAY = "Topic" + MQ_ENTITY_PAY;
	public static final String PRODUCER_GROUP_PAY = "ProducerGroup"
			+ MQ_ENTITY_PAY;

	public static DefaultMQProducer setNamesrvAddr(DefaultMQProducer producer) {
		producer.setNamesrvAddr(MQ_ADDRESS);
		return producer;
	}
}
