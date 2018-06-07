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

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import com.evangel.rocketmq.example.util.ExampleUtil;

public class Producer {
	public static void main(String[] args) throws MQClientException,
			InterruptedException {
		DefaultMQProducer producer = new DefaultMQProducer(
				ExampleUtil.PRODUCER_GROUP);
		producer = ExampleUtil.setNamesrvAddr(producer);
		producer.start();
		try {
			for (int i = 0; i < 6000000; i++) {
				Message msg = new Message("TopicFilter7", "TagA", "OrderID001",
						"Hello world".getBytes(ExampleUtil.DEFAULT_CHARSET));
				msg.putUserProperty("SequenceId", String.valueOf(i));
				SendResult sendResult = producer.send(msg);
				System.out.printf("%s%n", sendResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		producer.shutdown();
	}
}
