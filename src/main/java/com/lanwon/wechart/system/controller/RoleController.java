/**
 * Project Name:kafa-back-web
 * File Name:RoleController.java
 * Package Name:com.kind.perm.web.system
 * Date:2016年6月13日下午6:09:25
 * Copyright (c) 2016, http://www.mcake.com All Rights Reserved.
 *
*/

package com.lanwon.wechart.system.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanwon.aop.shiro.session.SessionConstants;
import com.lanwon.common.page.PageQuery;
import com.lanwon.common.page.PageView;
import com.lanwon.common.util.NumberUtils;
import com.lanwon.wechart.base.controller.BaseController;
import com.lanwon.wechart.base.entity.DataGrid;
import com.lanwon.wechart.system.entity.Role;
import com.lanwon.wechart.system.entity.User;
import com.lanwon.wechart.system.service.MenuService;
import com.lanwon.wechart.system.service.RoleService;


/**
 * Function:RoleController. <br/>
 * Date: 2016年6月13日 下午6:09:25 <br/>
 * 
 * @author weiguo21
 * @version
 * @since JDK 1.7
 * @see
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {
	@Resource
	private RoleService roleService;

	@Resource
	private MenuService menuService;

	/**
	 * 默认页面.
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toRoleList() {
		return "system/role_list";
	}

	/**
	 * 角色分页.
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/json", method = RequestMethod.GET)
	@ResponseBody
	public DataGrid pageRole(PageQuery query, HttpServletRequest request) {
		PageView<Role> pager = roleService.pageRole(query);
		return this.buildDataGrid(pager);
	}

	/**
	 * 获取角色拥有的权限id集合.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}/json")
	@ResponseBody
	public List<Long> getRolePermissions(@PathVariable("id") Long id) {
		List<Long> permIds = menuService.getPermIdsByRoleId(id);
		return permIds;
	}

	/**
	 * 修改角色权限
	 * 
	 * @param id
	 * @param newRoleList
	 * @return
	 */
	@RequestMapping(value = "/{id}/changeRolePerm")
	@ResponseBody
	public String changeRolePermission(@PathVariable("id") Long roleId, @RequestBody List<Long> targetIds,
			HttpSession session) {
		List<Long> originIds = menuService.getPermIdsByRoleId(roleId);
		/**
		 * 获取application中的sessions
		 */
		@SuppressWarnings("unchecked")
		HashSet<Session> sessions = (HashSet<Session>) session.getServletContext().getAttribute("sessions");
		if (null != sessions) {
			this.cleanCurrentUserPermCache(roleId, sessions);
		}
		menuService.changeRoleMenu(roleId, originIds, targetIds);

		return "success";
	}

	/**
	 * 当前如果有正在使用的用户，需要更新正在使用的用户的权限
	 */
	/**
	 * @param roleId
	 * @param sessions
	 */
	private void cleanCurrentUserPermCache(Long roleId, HashSet<Session> sessions) {
		Iterator<Session> iterator = sessions.iterator();
		PrincipalCollection pc = null;
		/**
		 * 遍历sessions
		 */
		while (iterator.hasNext()) {
			HttpSession session = (HttpSession) iterator.next();
			User user = (User) session.getAttribute(SessionConstants.SESSION_USER_KEY);
			if (user.getId() == roleId) {
				pc = (PrincipalCollection) session.getAttribute(String.valueOf(roleId));
				/**
				 * 清空该用户权限缓存
				 */
				menuService.clearUserPermCache(pc);
				session.removeAttribute(String.valueOf(roleId));
				break;
			}
		}
	}

	/**
	 * 添加角色跳转
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toSave", method = RequestMethod.GET)
	public String toSave(Model model) {
		model.addAttribute("role", new Role());
		model.addAttribute("action", "save");
		return "system/role_form";
	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid Role role, Model model) {
		roleService.saveRole(role);
		return "success";
	}

	/**
	 * 修改角色跳转.
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/change/{id}", method = RequestMethod.GET)
	public String toChange(@PathVariable("id") Long id, Model model) {
		model.addAttribute("role", roleService.getRoleById(id));
		model.addAttribute("action", "change");
		return "system/role_form";
	}

	/**
	 * 修改角色.
	 * 
	 * @param role
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/change", method = RequestMethod.POST)
	@ResponseBody
	public String change(@ModelAttribute("role") Role role, Model model) {
		roleService.changeRole(role);
		return "success";
	}

	/**
	 * 删除角色.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/remove/{id}")
	@ResponseBody
	public String remove(@PathVariable("id") Long roleId) {
		if (!NumberUtils.isEmptyLong(roleId)) {
			roleService.removeRoleById(roleId);
		}
		return "success";
	}

	@ModelAttribute
	public void getRole(@RequestParam(value = "id", defaultValue = "-1") Long roleId, Model model) {
		if (roleId != -1) {
			model.addAttribute("role", roleService.getRoleById(roleId));
		}
	}
}
