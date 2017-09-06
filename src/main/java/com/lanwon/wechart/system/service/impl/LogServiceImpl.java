/**
 * Project Name:kafa-wheat-core
 * File Name:LogServiceImpl.java
 * Package Name:com.kind.perm.core.service.impl
 * Date:2016年5月17日下午7:58:17
 * Copyright (c) 2016, weiguo.liu@mcake.com All Rights Reserved.
 *
*/

package com.lanwon.wechart.system.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.lanwon.wechart.system.entity.LoginLog;
import com.lanwon.wechart.system.entity.OpLog;
import com.lanwon.wechart.system.mapper.LoginLogMapper;
import com.lanwon.wechart.system.mapper.OpLogMapper;
import com.lanwon.wechart.system.service.LogService;


/**
 * 
 * @see :日志管理.
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class LogServiceImpl implements LogService {
	
	private final static Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
	
	@Autowired
	private LoginLogMapper loginLogMapper;
	
	@Autowired
	private OpLogMapper opLogMapper;

	@Override
	public void saveLoginLog(LoginLog log) {
		loginLogMapper.save(log);
	}

	@Override
	public void saveOpLog(OpLog opLog) {
		opLogMapper.saveOpLog(opLog);
	}

}
