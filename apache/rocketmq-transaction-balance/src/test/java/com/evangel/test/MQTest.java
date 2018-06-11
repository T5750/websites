package com.evangel.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.rocketmq.client.QueryResult;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.evangel.mq.MQConsumer;
import com.evangel.mq.MQProducer;
import com.evangel.util.Globals;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(rollbackFor = Exception.class)
public class MQTest {
	@Autowired
	private MQConsumer mQConsumer;

	@Test
	public void testMQProducer() throws Exception {
		MQProducer.main(new String[1]);
	}

	@Test
	public void testMQConsumer() throws Exception {
		System.out.println(this.mQConsumer);
		long end = new Date().getTime();
		long begin = end - 60 * 1000 * 60 * 24;
		QueryResult qr = this.mQConsumer.queryMessage(Globals.TOPIC,
				Globals.KEYS, 10, begin, end);
		List<MessageExt> list = qr.getMessageList();
		for (MessageExt me : list) {
			Map<String, String> m = me.getProperties();
			System.out.println(m.keySet().toString());
			System.out.println(m.values().toString());
			System.out.println(me.toString());
			System.out
					.println("Prepared :" + me.getPreparedTransactionOffset());
			System.err.println("内容: "
					+ new String(me.getBody(), Globals.DEFAULT_CHARSET));
		}
	}
}
