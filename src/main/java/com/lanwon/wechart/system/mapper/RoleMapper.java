package com.lanwon.wechart.system.mapper;

import java.util.List;

import com.lanwon.common.page.PageQuery;
import com.lanwon.wechart.base.dao.BaseMapper;
import com.lanwon.wechart.system.entity.Role;

public interface RoleMapper extends BaseMapper<Role>{

	/**
	 * 分页加载角色信息.
	 * 
	 * @param query
	 * @return
	 */
	public List<Role> page(PageQuery query);

	/**
	 * 获得记录数.
	 * 
	 * @param query
	 * @return
	 */
	public int count(PageQuery query);

	/**
	 * 保存角色.
	 * 
	 * @param roleDO
	 */
	public void save(Role roleDO);

	/**
	 * 修改角色.
	 * 
	 * @param roleDO
	 */
	public void change(Role roleDO);

	/**
	 * 根据id查询角色信息.
	 * 
	 * @param id
	 * @return
	 */
	public Role getById(Long id);

	/**
	 * 根据id删除角色.
	 * 
	 * @param id
	 */
	public void remove(Long id);
}
