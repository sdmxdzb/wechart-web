package com.lanwon.wechart.system.mapper;

import java.util.List;

import com.lanwon.common.request.UserQueryRequest;
import com.lanwon.wechart.base.dao.BaseMapper;
import com.lanwon.wechart.system.entity.User;


public interface UserMapper extends BaseMapper<User>{


	/**
	 * 获取用户
	 * 
	 * @param userDO
	 * @return
	 */
	public User getByUsername(String username);

	/**
	 * 保存用户信息
	 * 
	 * @param user
	 */
	public void saveUser(User userDO);

	/**
	 * 修改用户密码
	 * 
	 * @param userDO
	 */
	public void changePassword(User userDO);

	/**
	 * 分页查询用户信息
	 * 
	 * @param request
	 * @return
	 */
	public List<User> page(UserQueryRequest request);

	/**
	 * 加载用户记录数
	 * 
	 * @param request
	 * @return
	 */
	public int count(UserQueryRequest request);

	/**
	 * 根据id加载数据
	 * 
	 * @param id
	 * @return
	 */
	public User getById(Long id);

	/**
	 * 修改用户信息(不包含密码)
	 * 
	 * @param userDO
	 */
	public void change(User userDO);

	/**
	 * 根据id删除用户
	 * 
	 * @param id
	 */
	public void removeById(Long id);
}
