package com.evangel.mapper;

import com.evangel.entity.Balance;

public interface BalanceMapper {
	int deleteByPrimaryKey(String userid);

	int insert(Balance record);

	int insertSelective(Balance record);

	Balance selectByPrimaryKey(String userid);

	int updateByPrimaryKeySelective(Balance record);

	int updateByPrimaryKey(Balance record);
}