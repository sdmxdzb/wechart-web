/**
 * Project Name:kind-common-base
 * File Name:BaseDaoMyBatisImpl.java
 * Package Name:com.kind.common.persistence.mybatis
 * Date:2016-4-28下午4:13:07
 * Copyright (c) 2016, http://www.kindapp.net All Rights Reserved.
 *
 */
package com.lanwon.wechart.base.dao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * Function:mybatis基类实现. <br/>
 * 
 * @author dzb
 * @version:@param <T>
 * @since:JDK 1.8
 * @date 2018/09/06
 */
public interface BaseMapper<T>{
	public int insert(T entity);
	public int update(T entity);
	public int updateByMap(Map<String, Object> params);
	public int delete(Long pk);
	public int deleteByMap(Map<String, Object> params);
	public int count(T entity);
	public T getById(Long pk);
	public T getForObject(T entity);
	public List<T> query(T entity);
	public List<T> queryForList(T entity) ;
	public List<T> queryByMap(Map<String, Object> params);
	public int batchInsert(List list);
	public int batchUpdate(List list);
	public int batchDelete(List list);


}
