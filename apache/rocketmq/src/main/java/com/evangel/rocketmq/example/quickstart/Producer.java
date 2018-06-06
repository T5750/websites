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
package com.evangel.rocketmq.example.quickstart;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.evangel.rocketmq.example.util.ExampleUtil;

/**
 * This class demonstrates how to send messages to brokers using provided
 * {@link DefaultMQProducer}.
 */
public class Producer {
	public static void main(String[] args) throws MQClientException,
			InterruptedException {
		/*
		 * Instantiate with a producer group name.
		 */
		DefaultMQProducer producer = new DefaultMQProducer(
				ExampleUtil.PRODUCER_GROUP);
		/*
		 * Specify name server addresses. <p/>
		 * 
		 * Alternatively, you may specify name server addresses via exporting
		 * environmental variable: NAMESRV_ADDR <pre> {@code
		 * producer.setNamesrvAddr("name-server1-ip:9876;name-server2-ip:9876");
		 * } </pre>
		 */
		/*
		 * Launch the instance.
		 */
		producer = ExampleUtil.setNamesrvAddr(producer);
		producer.start();
		for (int i = 0; i < 1000; i++) {
			try {
				/*
				 * Create a message instance, specifying topic, tag and message
				 * body.
				 */
				Message msg = new Message(ExampleUtil.TOPIC /* Topic */,
						"TagAProducer" /* Tag */,
						("Hello RocketMQ " + i)
								.getBytes(ExampleUtil.DEFAULT_CHARSET) /*
																		 * Message
																		 * body
																		 */
				);
				/*
				 * Call send message to deliver message to one of brokers.
				 */
				SendResult sendResult = producer.send(msg);
				System.out.printf("%s%n", sendResult);
			} catch (Exception e) {
				e.printStackTrace();
				Thread.sleep(1000);
			}
		}
		/*
		 * Shut down once the producer instance is not longer in use.
		 */
		producer.shutdown();
	}
}
