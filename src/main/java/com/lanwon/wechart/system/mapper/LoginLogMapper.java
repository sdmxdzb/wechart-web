package com.lanwon.wechart.system.mapper;

import com.lanwon.wechart.base.dao.BaseMapper;
import com.lanwon.wechart.system.entity.LoginLog;

public interface LoginLogMapper  extends BaseMapper<LoginLog>{
	/**
	 * 保存日志.
	 * 
	 * @param logDO
	 */
	public void save(LoginLog logDO);
}
