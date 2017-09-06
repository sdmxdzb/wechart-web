package com.lanwon.wechart.system.mapper;

import java.util.List;
import java.util.Map;

import com.lanwon.wechart.base.dao.BaseMapper;
import com.lanwon.wechart.system.entity.RoleMenu;

public interface RoleMenuMapper extends BaseMapper<RoleMenu>{

	/**
	 * 根据id查询.
	 * 
	 * @param id
	 * @return
	 */
	public RoleMenu getyById(Long id);

	/**
	 * 根据角色id查询权限.
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Long> getPermIdsByRoleId(Long roleId);

	/**
	 * 保存.
	 * 
	 * @param rolePermissionDO
	 */
	public void save(RoleMenu rolePermission);

	/**
	 * 修改.
	 * 
	 * @param rolePermissionDO
	 */
	public void change(RoleMenu rolePermission);

	/**
	 * 根据id删除.
	 * 
	 * @param id
	 */
	public void removeById(Long id);

	/**
	 * 根据角色id和permissionId删除.
	 * 
	 * @param roleId
	 * @param permissionId
	 */
	public void removeByParamMap(Map<String,Object> params);

}
