package com.evangel.mq;

import java.util.Map;

import org.apache.rocketmq.client.producer.LocalTransactionExecuter;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.evangel.entity.Pay;
import com.evangel.service.PayService;
import com.evangel.util.FastJsonConvert;
import com.evangel.util.Globals;

/**
 * 执行本地事务，由客户端回调
 */
// @Scope("prototype")
@Component("transactionExecuterImpl")
public class TransactionExecuterImpl implements LocalTransactionExecuter {
	@Autowired
	private PayService payService;

	@Override
	public LocalTransactionState executeLocalTransactionBranch(Message msg,
			Object arg) {
		try {
			// Message Body
			JSONObject messageBody = FastJsonConvert.convertJSONToObject(
					new String(msg.getBody(), Globals.DEFAULT_CHARSET),
					JSONObject.class);
			// Transaction MapArgs
			Map<String, Object> mapArgs = (Map<String, Object>) arg;
			// --------------------IN PUT---------------------- //
			System.out.println("message body = " + messageBody);
			System.out.println("message mapArgs = " + mapArgs);
			System.out.println("message tag = " + msg.getTags());
			// --------------------IN PUT---------------------- //
			// userid
			String userid = messageBody.getString("userid");
			// money
			double money = messageBody.getDouble("money");
			// mode
			String pay_mode = messageBody.getString("pay_mode");
			// pay
			Pay pay = this.payService.selectByPrimaryKey(userid);
			// 持久化数据
			this.payService.updateAmount(pay, pay_mode, money);
			// 成功通知MQ消息变更 该消息变为：<确认发送>
			return LocalTransactionState.COMMIT_MESSAGE;
			// return LocalTransactionState.UNKNOW;
		} catch (Exception e) {
			e.printStackTrace();
			// 失败则不通知MQ 该消息一直处于：<暂缓发送>
			return LocalTransactionState.ROLLBACK_MESSAGE;
		}
	}
}