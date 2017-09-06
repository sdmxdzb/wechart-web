/**
 * Project Name:kafa-wheat-core
 * File Name:MenuServiceImpl.java
 * Package Name:com.kind.perm.core.service.impl
 * Date:2016年5月17日下午7:58:17
 * Copyright (c) 2016, http://www.mcake.com All Rights Reserved.
 *
*/

package com.lanwon.wechart.system.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lanwon.aop.shiro.MyShiroRealm;
import com.lanwon.wechart.system.entity.Menu;
import com.lanwon.wechart.system.entity.RoleMenu;
import com.lanwon.wechart.system.mapper.MenuMapper;
import com.lanwon.wechart.system.mapper.RoleMenuMapper;
import com.lanwon.wechart.system.service.MenuService;


/**
 * 
 * @see 权限管理实现. <br/>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MenuServiceImpl implements MenuService {
	
	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
	@Resource
	private MenuMapper menuMapper;

	@Resource
	private RoleMenuMapper rolemenuMapper;

	@Override
	public List<Menu> getAllMenus() {
		return menuMapper.getAllMenus();
	}

	@Override
	public List<Menu> getAll() {
		return menuMapper.getAll();
	}

	@Override
	public void save(Menu Menu) {
		menuMapper.save(Menu);
	}

	@Override
	public void change(Menu Menu) {
		menuMapper.change(Menu);
	}

	@Override
	public Menu getById(Long id) {
		return menuMapper.getById(id);
	}

	@Override
	public void removeById(Long id) {
		menuMapper.removeById(id);
	}

	@Override
	public List<Long> getPermIdsByRoleId(Long roleId) {
		return rolemenuMapper.getPermIdsByRoleId(roleId);
	}

	public void changeRoleMenu(Long roleId, List<Long> originIds, List<Long> targetIds) {
		logger.debug("修改角色权限:roleId{}" + roleId);
		/**
		 * 是否删除
		 */
		for (Long permId : originIds) {
			if (!targetIds.contains(permId)) {
			Map<String, Object>	map=new HashMap<String, Object>();
			map.put("roleId", roleId);
			map.put("permissionId", permId);
				rolemenuMapper.removeByParamMap(map);
			}
		}
		/**
		 * 是否添加
		 */
		for (Long permId : targetIds) {
			if (!originIds.contains(permId)) {
				rolemenuMapper.save(this.makeRoleMenu(roleId, permId));
			}
		}

	}

	private RoleMenu makeRoleMenu(Long roleId, Long MenuId) {
		RoleMenu roleMenu = new RoleMenu();
		roleMenu.setRoleId(roleId);
		roleMenu.setMenuId(MenuId);
		return roleMenu;
	}

	public void clearUserPermCache(PrincipalCollection pc) {
		RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		MyShiroRealm userRealm = (MyShiroRealm) securityManager.getRealms().iterator().next();
		userRealm.clearCachedAuthorizationInfo(pc);
	}

	@Override
	public List<Menu> getMenuOperations(Long parentId) {
		return menuMapper.queryMenuOperation(parentId);
	}

	@Override
	public List<Menu> getMenusByUserId(Long userId) {
		return menuMapper.getMenusByUserId(userId);
	}

}
