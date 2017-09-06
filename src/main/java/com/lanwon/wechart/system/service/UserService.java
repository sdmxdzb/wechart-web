package com.lanwon.wechart.system.service;

import java.util.List;

import com.lanwon.common.page.PageView;
import com.lanwon.common.request.PasswordRequest;
import com.lanwon.common.request.UserQueryRequest;
import com.lanwon.common.result.Result;
import com.lanwon.wechart.system.entity.User;
import com.lanwon.wechart.system.entity.UserRole;



public interface UserService {
	
	
	public List<User> query(User entity);
	
	
	/**
	 * 按登录名查询用户(配置文件)
	 * 
	 * @param username
	 * @return 用户对象
	 */
	public User getByUsername(String username);

	/**
	 * 保存用户信息
	 * 
	 * @param userDO
	 */
	public void saveUser(User userDO);

	/**
	 * 修改用户密码
	 * 
	 * @param userDO
	 */
	public Result changePassword(PasswordRequest passwordRequest);

	/**
	 * 用户信息分页
	 * 
	 * @param request
	 * @return
	 */
	public PageView<User> pageUser(UserQueryRequest request);

	/**
	 * 根据id加载数据
	 * 
	 * @param id
	 * @return
	 */
	public User getById(Long id);

	/**
	 * 修改用户信息
	 * 
	 * @param userDO
	 */
	public void changeUser(User user);

	/**
	 * 删除用户
	 * 
	 * @param id
	 */
	public void remove(Long id);

	/**
	 * 获取用户角色.
	 */
	public List<UserRole> getUserRoles(Long userId);

	/**
	 * 修改用户角色.
	 * 
	 * @param userId
	 * @param originRoleIds
	 * @param targetRoleIds
	 */
	public void changeUserRole(Long userId, List<Long> originRoleIds, List<Long> targetRoleIds);

	/**
	 * 记录登录时间.
	 * 
	 * @param userDO
	 */
	public void saveLoginTime(User userDO);

	/**
	 * 验证密码.
	 * 
	 * @param user
	 * @param oldPassword
	 * @return
	 */
	public boolean checkPassword(User user, String oldPassword);

}
