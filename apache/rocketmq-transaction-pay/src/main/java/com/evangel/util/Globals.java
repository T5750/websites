package com.evangel.util;

public class Globals {
	public static final String IP_ADDRESS = "127.0.0.1";
	public static final String MQ_ADDRESS = IP_ADDRESS + ":9876";
	public static final String DEFAULT_CHARSET = "UTF-8";
	public static final String MQ_ENTITY = "Pay";
	public static final String TOPIC = "Topic" + MQ_ENTITY;
	public static final String PRODUCER_GROUP = "ProducerGroup" + MQ_ENTITY;
	public static final String TAGS = "Tag" + MQ_ENTITY;
}
