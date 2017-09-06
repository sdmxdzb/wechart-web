package com.lanwon.wechart.system.service;

import com.lanwon.common.page.PageQuery;
import com.lanwon.common.page.PageView;
import com.lanwon.wechart.system.entity.Role;

public interface RoleService {
	/**
	 * 分页加载所有角色.
	 * 
	 * @param query
	 * @return
	 */
	public PageView<Role> pageRole(PageQuery query);

	/**
	 * 保存角色.
	 * 
	 * @param roleDO
	 */
	public void saveRole(Role roleDO);

	/**
	 * 修改角色.
	 * 
	 * @param roleDO
	 */
	public void changeRole(Role roleDO);

	/**
	 * 根据id获取角色.
	 * 
	 * @param roleId
	 * @return
	 */
	public Role getRoleById(Long roleId);

	/**
	 * 根据roleId删除.
	 * 
	 * @param roleId
	 */
	public void removeRoleById(Long roleId);
}
