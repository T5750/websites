package com.evangel.multiTopic;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.evangel.multiTopic.util.Globals;

public class PushConsumer {
	/**
	 * 当前例子是PushConsumer用法，使用方式给用户感觉是消息从RocketMQ服务器推到了应用客户端。<br>
	 * 但是实际PushConsumer内部是使用长轮询Pull方式从MetaQ服务器拉消息，然后再回调用户Listener方法<br>
	 */
	public static void main(String[] args) throws InterruptedException,
			MQClientException {
		/**
		 * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例<br>
		 * 注意：ConsumerGroupName需要由应用来保证唯一
		 */
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
				Globals.CONSUMER_GROUP);
		consumer = Globals.setNamesrvAddr(consumer);
		consumer.setInstanceName(Globals.CONSUMER_INSTANCE_NAME);
		/**
		 * 订阅指定topic下tags分别等于TagA或TagC或TagD
		 */
		consumer.subscribe(Globals.TOPIC_ONE, "TagA || TagC || TagD");
		/**
		 * 订阅指定topic下所有消息<br>
		 * 注意：一个consumer对象可以订阅多个topic
		 */
		consumer.subscribe(Globals.TOPIC_TWO, "*");
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			/**
			 * 默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
			 */
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(
					List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				MessageExt msg = msgs.get(0);
				System.out.println("[" + msg.getTopic() + "]"
						+ Thread.currentThread().getName()
						+ " Receive New Messages: " + msgs.size());
				if (msg.getTopic().equals(Globals.TOPIC_ONE)) {
					// 执行TopicTest1的消费逻辑
					if (msg.getTags() != null && msg.getTags().equals("TagA")) {
						// 执行TagA的消费
						System.out.println("[" + Globals.TOPIC_ONE + "-TagA]"
								+ new String(msg.getBody()));
					} else if (msg.getTags() != null
							&& msg.getTags().equals("TagC")) {
						// 执行TagC的消费
						System.out.println("[" + Globals.TOPIC_ONE + "-TagC]"
								+ new String(msg.getBody()));
					} else if (msg.getTags() != null
							&& msg.getTags().equals("TagD")) {
						// 执行TagD的消费
						System.out.println("[" + Globals.TOPIC_ONE + "-TagD]"
								+ new String(msg.getBody()));
					}
				} else if (msg.getTopic().equals(Globals.TOPIC_TWO)) {
					System.out.println("[" + Globals.TOPIC_TWO + "]"
							+ new String(msg.getBody()));
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		/**
		 * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
		 */
		consumer.start();
		System.out.println("Consumer Started for Multi Topics.");
	}
}