package com.evangel;

import java.util.*;

import org.apache.rocketmq.client.QueryResult;
import org.apache.rocketmq.client.producer.LocalTransactionExecuter;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.evangel.mq.MQProducer;
import com.evangel.util.FastJsonConvert;
import com.evangel.util.Globals;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(rollbackFor = Exception.class)
public class PayTest {
	@Autowired
	private MQProducer mQProducer;
	@Autowired
	private LocalTransactionExecuter transactionExecuterImpl;

	@Test
	public void testMQProducer() {
		try {
			System.out.println(this.mQProducer);
			System.out.println(this.transactionExecuterImpl);
			// 构造消息数据
			Message message = new Message();
			// 主题
			message.setTopic(Globals.TOPIC);
			// 子标签
			message.setTags(Globals.TAGS);
			// key
			String uuid = UUID.randomUUID().toString();
			System.out.println("key: " + uuid);
			message.setKeys(uuid);
			JSONObject body = new JSONObject();
			body.put("userid", "z3");
			body.put("money", "1000");
			body.put("pay_mode", "OUT");
			body.put("balance_mode", "IN");
			message.setBody(FastJsonConvert.convertObjectToJSON(body)
					.getBytes());
			// 添加参数
			Map<String, Object> transactionMapArgs = new HashMap<String, Object>();
			this.mQProducer.sendTransactionMessage(message,
					this.transactionExecuterImpl, transactionMapArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMQProducerList() throws Exception {
		long end = new Date().getTime();
		long begin = end - 60 * 1000 * 60 * 24;
		QueryResult qr = this.mQProducer.queryMessage(Globals.TOPIC,
				"c406d986-259f-44b0-a835-82d47d197538", 10, begin, end);
		List<MessageExt> list = qr.getMessageList();
		for (MessageExt me : list) {
			Map<String, String> m = me.getProperties();
			System.out.println(m.keySet().toString());
			System.out.println(m.values().toString());
			System.out.println(me.toString());
			System.err.println("内容: "
					+ new String(me.getBody(), Globals.DEFAULT_CHARSET));
			System.out
					.println("Prepared :" + me.getPreparedTransactionOffset());
			LocalTransactionState ls = this.mQProducer.check(me);
			System.out.println(ls);
			// this.mQProducer.getTransactionCheckListener()
		}
		// System.out.println("qr: " + qr.toString());
		// C0A8016F00002A9F0000000000034842
	}
}
