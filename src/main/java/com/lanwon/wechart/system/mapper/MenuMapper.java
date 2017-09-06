package com.lanwon.wechart.system.mapper;

import java.util.List;

import com.lanwon.wechart.base.dao.BaseMapper;
import com.lanwon.wechart.system.entity.Menu;



public interface MenuMapper extends BaseMapper<Menu>{

	/**
	 * 加载菜单数据.
	 * 
	 * @return
	 */
	public List<Menu> getAllMenus();

	/**
	 * 查询全部数据.
	 * 
	 * @return
	 */
	public List<Menu> getAll();

	/**
	 * 保存权限.
	 * 
	 * @param permissionDO
	 */
	public void save(Menu permissionDO);

	/**
	 * 修改权限.
	 * 
	 * @param permissionDO
	 */
	public void change(Menu permissionDO);

	/**
	 * 根据id查询权限信息.
	 * 
	 * @param id
	 * @return
	 */
	public Menu getById(Long id);

	/**
	 * 根据id删除权限.
	 * 
	 * @param id
	 */
	public void removeById(Long id);

	/**
	 * 查询菜单下的操作权限.
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Menu> queryMenuOperation(Long parentId);

	/**
	 * 获取用户权限.
	 * 
	 * @param userId
	 * @return
	 */
	public List<Menu> getMenusByUserId(Long userId);

}
