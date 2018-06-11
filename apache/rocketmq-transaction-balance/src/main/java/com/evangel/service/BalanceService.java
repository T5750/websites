package com.evangel.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evangel.entity.Balance;
import com.evangel.mapper.BalanceMapper;

@Service
public class BalanceService {
	@Autowired
	private BalanceMapper balanceMapper;

	public int insert(Balance record) {
		return this.balanceMapper.insert(record);
	}

	public Balance selectByPrimaryKey(String userid) {
		return this.balanceMapper.selectByPrimaryKey(userid);
	}

	public int updateByPrimaryKey(Balance record) {
		return this.balanceMapper.updateByPrimaryKey(record);
	}

	@Transactional
	public void updateAmount(String userid, String mode, double money)
			throws Exception {
		Balance balance = this.selectByPrimaryKey(userid);
		if ("IN".equals(mode)) {
			balance.setAmount(balance.getAmount() + Math.abs(money));
		} else if ("OUT".equals(mode)) {
			balance.setAmount(balance.getAmount() - Math.abs(money));
		}
		balance.setUpdateTime(new Date());
		this.updateByPrimaryKey(balance);
	}
}
