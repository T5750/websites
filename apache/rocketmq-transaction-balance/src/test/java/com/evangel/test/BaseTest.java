package com.evangel.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.evangel.entity.Balance;
import com.evangel.service.BalanceService;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional(rollbackFor = Exception.class)
public class BaseTest {
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
		// this.balanceService.insertSelective(balance);
	}

	@Test
	public void testUpdate() throws Exception {
		Balance balance = this.balanceService.selectByPrimaryKey("z3");
		balance.setAmount(balance.getAmount() + 1000d);
		balance.setUpdateTime(new Date());
		this.balanceService.updateByPrimaryKey(balance);
	}
}
