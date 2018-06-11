package com.evangel;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.evangel.entity.Pay;
import com.evangel.service.PayService;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(rollbackFor = Exception.class)
public class BaseTest {
	@Autowired
	private PayService payService;

	@Test
	public void testSave() throws Exception {
		Pay pay = new Pay();
		pay.setUserid("z3");
		pay.setUsername("张三");
		pay.setAmount(5000d);
		pay.setDetail("0");
		pay.setUpdateBy("z3");
		pay.setUpdateTime(new Date());
		this.payService.insert(pay);
	}

	@Test
	public void testUpdate() throws Exception {
		System.out.println(this.payService);
		Pay pay = this.payService.selectByPrimaryKey("z3");
		pay.setAmount(pay.getAmount() - 1000d);
		pay.setUpdateTime(new Date());
		this.payService.updateByPrimaryKey(pay);
	}
}
