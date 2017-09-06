package com.lanwon.wechart.system.mapper;

import com.lanwon.common.exception.MapperException;
import com.lanwon.wechart.base.dao.BaseMapper;
import com.lanwon.wechart.system.entity.OpLog;

public interface OpLogMapper extends BaseMapper<OpLog>{
	/**
	 * 保存操作日志.
	 * 
	 * @param opLog
	 * @throws MapperException
	 */
	public void saveOpLog(OpLog opLog) throws MapperException;
}
