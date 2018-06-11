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

import com.evangel.entity.Balance;
import com.evangel.mq.MQConsumer4Pay;
import com.evangel.service.BalanceService;
import com.evangel.util.Globals;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(rollbackFor = Exception.class)
public class BaseTest {
	@Autowired
	private MQConsumer4Pay mqConsumer4Pay;
	@Autowired
	private BalanceService balanceService;

	@Test
	public void testSave() throws Exception {
		Balance balance = new Balance();
		balance.setUserid("z3");
		balance.setUsername("张三");
		balance.setAmount(5000d);
		balance.setUpdateBy("z3");
		balance.setUpdateTime(new Date());
		this.balanceService.insert(balance);
	}

	@Test
	public void testUpdate() throws Exception {
		Balance balance = this.balanceService.selectByPrimaryKey("z3");
		balance.setAmount(balance.getAmount() + 1000d);
		balance.setUpdateTime(new Date());
		this.balanceService.updateByPrimaryKey(balance);
	}

	/**
	 * for rocketmq-transaction-pay
	 */
	@Test
	public void testMQConsumer4Pay() throws Exception {
		System.out.println(this.mqConsumer4Pay);
		long end = new Date().getTime();
		long begin = end - 60 * 1000 * 60 * 24;
		QueryResult qr = this.mqConsumer4Pay.queryMessage(Globals.TOPIC_PAY,
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
		}
	}
}
