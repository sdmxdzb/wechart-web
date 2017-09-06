package com.lanwon.wechart.system.mapper;

import java.util.List;

import com.lanwon.common.page.PageQuery;
import com.lanwon.wechart.base.dao.BaseMapper;
import com.lanwon.wechart.system.entity.Community;



public interface CommunityMapper extends BaseMapper<Community>{

	/**
	 * 
	 * page:分页查询小区信息<br/>
	 * @param query
	 * @return
	 */
	public List<Community> page(PageQuery query);

	/**
	 * 查询记录数.
	 * count:. <br/>
	 * @param query
	 * @return
	 */
	public int count(PageQuery query);
	/**
	 * 
	 * save:保存小区信息<br/>
	 * @param community
	 */
	public void save(Community community);
	/**
	 * 
	 * remove:删除小区信息 <br/>
	 * @param id
	 */
	public void remove(Long id);
	/**
	 * 
	 * getById:根据id加载<br/>
	 *
	 * @author weiguo.liu
	 * @param id
	 * @return
	 */
	public Community getById(Long id);
}
