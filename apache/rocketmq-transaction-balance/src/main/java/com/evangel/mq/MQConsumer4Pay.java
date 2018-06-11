package com.evangel.mq;

import java.util.List;

import org.apache.rocketmq.client.QueryResult;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.evangel.service.BalanceService;
import com.evangel.util.FastJsonConvert;
import com.evangel.util.Globals;

@Component
public class MQConsumer4Pay {
	private final String GROUP_NAME = Globals.PRODUCER_GROUP_PAY;
	// private final String NAMESRV_ADDR =
	// "192.168.1.111:9876;192.168.1.112:9876;192.168.1.113:9876;192.168.1.114:9876";
	private final String NAMESRV_ADDR = Globals.MQ_ADDRESS;
	private DefaultMQPushConsumer consumer;
	@Autowired
	private BalanceService balanceService;

	public MQConsumer4Pay() {
		try {
			this.consumer = new DefaultMQPushConsumer(GROUP_NAME);
			this.consumer.setNamesrvAddr(NAMESRV_ADDR);
			this.consumer
					.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
			this.consumer.subscribe(Globals.TOPIC_PAY, "*");
			this.consumer.registerMessageListener(new Listener());
			this.consumer.start();
			System.out.println("consumer start for rocketmq-transaction-pay");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public QueryResult queryMessage(String topic, String key, int maxNum,
			long begin, long end) throws Exception {
		long current = System.currentTimeMillis();
		return this.consumer.queryMessage(topic, key, maxNum, begin, end);
	}

	class Listener implements MessageListenerConcurrently {
		@Override
		public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
				ConsumeConcurrentlyContext context) {
			MessageExt msg = msgs.get(0);
			try {
				String topic = msg.getTopic();
				// Message Body
				JSONObject messageBody = FastJsonConvert.convertJSONToObject(
						new String(msg.getBody(), Globals.DEFAULT_CHARSET),
						JSONObject.class);
				String tags = msg.getTags();
				String keys = msg.getKeys();
				System.out.println("balance4Pay服务收到消息, keys : " + keys
						+ ", body : "
						+ new String(msg.getBody(), Globals.DEFAULT_CHARSET));
				// userid
				String userid = messageBody.getString("userid");
				// money
				double money = messageBody.getDouble("money");
				// mode
				String balance_mode = messageBody.getString("balance_mode");
				// 业务逻辑处理
				balanceService.updateAmount(userid, balance_mode, money);
			} catch (Exception e) {
				e.printStackTrace();
				// 重试次数为3情况
				if (msg.getReconsumeTimes() == 3) {
					return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
					// 记录日志
				}
				return ConsumeConcurrentlyStatus.RECONSUME_LATER;
			}
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		}
	}
}
