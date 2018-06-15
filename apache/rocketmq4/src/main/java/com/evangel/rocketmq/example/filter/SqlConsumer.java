/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.evangel.rocketmq.example.filter;

import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import com.evangel.rocketmq.example.util.Globals;

public class SqlConsumer {
	public static void main(String[] args) {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(
				Globals.CONSUMER_GROUP_SQL);
		try {
			consumer.subscribe(Globals.TOPIC_SQL, MessageSelector
					.bySql("(TAGS is not null and TAGS in ('TagA', 'TagB'))"
							+ "and (a is not null and a between 0  3)"));
		} catch (MQClientException e) {
			e.printStackTrace();
			return;
		}
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(
					List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				System.out.printf("%s Receive New Messages: %s %n", Thread
						.currentThread().getName(), msgs);
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		try {
			consumer = Globals.setNamesrvAddr(consumer);
			consumer.start();
		} catch (MQClientException e) {
			e.printStackTrace();
			return;
		}
		System.out.printf("Consumer Started.%n");
	}
}
