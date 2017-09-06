package com.lanwon.wechart.system.mapper;

import java.util.List;

import com.lanwon.wechart.base.dao.BaseMapper;
import com.lanwon.wechart.system.entity.UserRole;


public interface UserRoleMapper  extends BaseMapper<UserRole>{
	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public UserRole getById(Long id);

	/**
	 * 保存
	 * 
	 * @param userRoleDO
	 */
	public void save(UserRole userRoleDO);

	/**
	 * 修改
	 * 
	 * @param userRoleDO
	 */
	public void change(UserRole userRoleDO);

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public void remove(Long userId, Long roleId);

	/**
	 * 获取用户角色.
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserRole> getUserRolesByUserId(Long userId);

}
