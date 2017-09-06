package com.lanwon.wechart.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanwon.aop.shiro.session.SessionUtils;
import com.lanwon.common.page.PageView;
import com.lanwon.common.request.PasswordRequest;
import com.lanwon.common.request.UserQueryRequest;
import com.lanwon.common.result.Result;
import com.lanwon.common.util.NumberUtils;
import com.lanwon.wechart.base.controller.BaseController;
import com.lanwon.wechart.base.entity.DataGrid;
import com.lanwon.wechart.system.entity.User;
import com.lanwon.wechart.system.entity.UserInputForm;
import com.lanwon.wechart.system.entity.UserRole;
import com.lanwon.wechart.system.service.UserService;



/**
 * 
 * @see :用户控制器
 * @date:2016年5月12日 上午11:17:52 
 * @author dzb
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	/**
	 * 默认页面.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toUserList(Model model) {
		return "system/user_list";
	}

	/**
	 * 获取用户JOSN数据.
	 */
	@RequestMapping(value = "json", method = RequestMethod.GET)
	@ResponseBody
	public DataGrid pageUser(UserQueryRequest userRequest, HttpServletRequest request) {
		PageView<User> page = userService.pageUser(userRequest);
		return super.buildDataGrid(page);
	}

	/**
	 * 添加用户跳转.
	 * 
	 * @param model
	 */
	@RequestMapping(value = "toSave", method = RequestMethod.GET)
	public String toSaveUser(Model model) {
		model.addAttribute("user", new UserInputForm(new User()));
		model.addAttribute("action", "save");
		return "system/user_form";
	}

	/**
	 * 添加用户.
	 * 
	 * @param user
	 * @param model
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String saveUser(@Valid User user, Model model) {
		logger.debug("user:" + user);
		if (userService.getByUsername(user.getUsername()) == null) {
			userService.saveUser(user);
			return "success";
		}
		String errorMsg = "用户名已经存在";
		return errorMsg;
	}

	@SuppressWarnings("unused")
	private Boolean checkUser(User user) {
		if (user == null) {
			return false;
		}
		return true;
	}

	/**
	 * 修改用户跳转.
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/change/{id}", method = RequestMethod.GET)
	public String toChangeUser(@PathVariable("id") Long id, Model model) {
		User user = userService.getById(id);
		model.addAttribute("user", new UserInputForm(user));
		model.addAttribute("action", "change");
		return "system/user_form";
	}

	/**
	 * 修改用户.
	 * 
	 * @param user
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:user:change")
	@RequestMapping(value = "change", method = RequestMethod.POST)
	@ResponseBody
	public String changeUser(@Valid @ModelAttribute @RequestBody User user, Model model) {
		userService.changeUser(user);
		return "success";
	}

	/**
	 * 删除用户.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/remove/{id}")
	@ResponseBody
	public String removeUser(@PathVariable("id") Long id) {
		if (!NumberUtils.isEmptyLong(id)) {
			userService.remove(id);
		}
		return "success";
	}

	/**
	 * 弹窗页-用户拥有的角色
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:user:roleView")
	@RequestMapping(value = "{userId}/userRole")
	public String toUserRoleList(@PathVariable("userId") Long id, Model model) {
		model.addAttribute("userId", id);
		return "system/user_role_list";
	}

	/**
	 * 获取用户拥有的角色ID集合
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/role")
	@ResponseBody
	public List<Long> getRoleIdList(@PathVariable("id") Long userId) {
		if (NumberUtils.isEmptyLong(userId)) {
			return null;
		}
		return getUserRoleIds(userId);
	}

	/**
	 * 修改用户拥有的角色
	 * 
	 * @param id
	 * @param newRoleList
	 * @return
	 */
	@RequestMapping(value = "/{id}/changeRole")
	@ResponseBody
	public String changeUserRole(@PathVariable("id") Long userId, @RequestBody List<Long> targetRoles) {
		userService.changeUserRole(userId, getRoleIdList(userId), targetRoles);
		return "success";
	}

	/**
	 * 包装数据.
	 * 
	 * @param userId
	 * @return
	 */
	private List<Long> getUserRoleIds(Long userId) {
		List<UserRole> roles = userService.getUserRoles(userId);
		List<Long> roleIds = new ArrayList<>();
		for (UserRole userRole : roles) {
			roleIds.add(userRole.getRoleId());
		}
		return roleIds;
	}

	/**
	 * 修改密码跳转
	 */
	@RequestMapping(value = "/toChangePasswod")
	public String toChangePassword(Model model, HttpSession session) {
		model.addAttribute("user", SessionUtils.getCurrentUser());
		return "system/password_change";
	}

	/**
	 * 修改密码
	 */
	@RequestMapping(value = "/changePassword")
	@ResponseBody
	public Result changePassword(String oldPassword,
			@Valid @ModelAttribute @RequestBody PasswordRequest passwordRequest, HttpSession session) {
		User user = SessionUtils.getCurrentUser();
		if (user != null && user.getId().equals(passwordRequest.getUserId())) {
			return userService.changePassword(passwordRequest);
		}
		return Result.build(300, "参数有误");

	}

	/**
	 * 校验用户名是否存在.
	 * 
	 * @param loginName
	 * @return
	 */
	@RequestMapping(value = "/checkUsername")
	@ResponseBody
	public String checkUsername(String username) {
		logger.debug("username:" + username);
		if (userService.getByUsername(username) == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * ajax请求校验原密码是否正确.
	 * 
	 * @param oldPassword
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkPassword")
	@ResponseBody
	public String checkPassword(@RequestParam(value = "originPassword", defaultValue = "") String originPassword,
			HttpServletRequest request) {
		logger.debug("originPassword:" + originPassword);
		User user = SessionUtils.getCurrentUser();
		if (userService.checkPassword(user, originPassword)) {
			return "true";
		}
		return "false";
	}

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出Task对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void getUser(@RequestParam(value = "id", defaultValue = "-1") Integer id, Model model) {
		/*
		 * if (id != -1) { model.addAttribute("user", userService.get(id)); }
		 */
	}

}
