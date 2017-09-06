package com.lanwon.wechart.system.service;

import java.util.List;
import org.apache.shiro.subject.PrincipalCollection;
import com.lanwon.wechart.system.entity.Menu;

public interface MenuService {
	/**
	 * 加载菜单数据.
	 * 
	 * @return
	 */
	public List<Menu> getAllMenus();

	/**
	 * 加载全部数据.
	 * 
	 * @return
	 */
	public List<Menu> getAll();

	/**
	 * 保存权限.
	 * 
	 * @param permissionDO
	 */
	public void save(Menu permission);

	/**
	 * 修改权限.
	 * 
	 * @param permissionDO
	 */
	public void change(Menu permission);

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
	 * 根据角色id查询权限.
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Long> getPermIdsByRoleId(Long roleId);

	/**
	 * 
	 * @param roleId
	 * @param riginIds
	 * @param targetIds
	 */
	public void changeRoleMenu(Long roleId, List<Long> orginIds, List<Long> targetIds);

	/**
	 * 清空该角色用户的权限缓存.
	 * 
	 * @param pc
	 */
	public void clearUserPermCache(PrincipalCollection pc);

	/**
	 * 查询菜单下的操作权限
	 * 
	 * @param parentId
	 * @return
	 */
	public List<Menu> getMenuOperations(Long parentId);

	/**
	 * 获取用户权限.
	 * 
	 * @param userId
	 * @return
	 */
	public List<Menu> getMenusByUserId(Long userId);
}
