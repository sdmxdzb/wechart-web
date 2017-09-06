package com.lanwon.wechart.system.service;

import com.lanwon.wechart.system.entity.LoginLog;
import com.lanwon.wechart.system.entity.OpLog;

public interface LogService {
	/**
	 * 保存登录日志.
	 * 
	 * @param log
	 */
	public void saveLoginLog(LoginLog logDO);

	/**
	 * 保存操作日志.
	 * 
	 * @param opLog
	 */
	public void saveOpLog(OpLog opLog);

}
