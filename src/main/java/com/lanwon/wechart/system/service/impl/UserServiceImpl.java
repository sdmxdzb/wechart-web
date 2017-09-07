package com.lanwon.wechart.system.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lanwon.aop.shiro.session.SessionUtils;
import com.lanwon.common.page.PageView;
import com.lanwon.common.request.PasswordRequest;
import com.lanwon.common.request.UserQueryRequest;
import com.lanwon.common.result.Result;
import com.lanwon.common.util.DateUtils;
import com.lanwon.common.util.MD5Utils;
import com.lanwon.common.util.StringUtils;
import com.lanwon.wechart.base.mapper.JSONMapper;
import com.lanwon.wechart.system.entity.User;
import com.lanwon.wechart.system.entity.UserRole;
import com.lanwon.wechart.system.mapper.UserMapper;
import com.lanwon.wechart.system.mapper.UserRoleMapper;
import com.lanwon.wechart.system.service.UserService;


/**
 * 
 * @see :用户管理. <br/>
 
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Resource
	private UserMapper userMapper;

	@Resource
	private UserRoleMapper userRoleMapper;


	@Override
	public User getByUsername(String username) {
		return userMapper.getByUsername(username);
	}

	/**
	 * 判断是否超级管理员
	 * 
	 * @param id
	 * @return boolean
	 */
	@SuppressWarnings("unused")
	private boolean isSupervisor(Integer id) {
		return id == 1;
	}

	/**
	 * 设定安全的密码
	 */
	@SuppressWarnings("unused")
	private void entryptPassword(User user) {
		user.setPassword(MD5Utils.getStringMD5(user.getPassword()));
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(MD5Utils.getStringMD5(user.getPassword()));
		userMapper.saveUser(user);
	}

	@Override
	public Result changePassword(PasswordRequest passwordRequest) {
		if (StringUtils.isEmpty(passwordRequest.getOriginPassword())
				|| StringUtils.isEmpty(passwordRequest.getTargetPassword())
				|| StringUtils.isEmpty(passwordRequest.getConfirmPassword())) {
			return Result.build(300, "修改密码参数有误");
		}
		Long userId = passwordRequest.getUserId();
		User user = userMapper.getById(userId);
		if (user == null) {
			return Result.build(300, "没有该用户");
		}
		if (!checkPassword(user, passwordRequest.getOriginPassword())) {
			return Result.build(300, "密码有误!");
		}
		user.setPassword(MD5Utils.getStringMD5(passwordRequest.getTargetPassword()));
		userMapper.changePassword(user);
		return Result.build(200, "密码修改成功!");

	}

	@Override
	public PageView<User> pageUser(UserQueryRequest request) {
		List<User> list = userMapper.page(request);
		int count = userMapper.count(request);
		request.setItems(count);
		return new PageView<>(request, list);
	}

	@Override
	public User getById(Long id) {
		return userMapper.getById(id);
	}

	@Override
	public void changeUser(User user) {
		userMapper.change(user);
	}

	@Override
	public void remove(Long id) {
		userMapper.removeById(id);
	}

	@Override
	public List<UserRole> getUserRoles(Long userId) {
		return userRoleMapper.getUserRolesByUserId(userId);
	}

	@Override
	public void changeUserRole(Long userId, List<Long> originRoleIds, List<Long> targetRoleIds) {

		/**
		 * 是否删除
		 */
		for (Long roleId : originRoleIds) {
			if (!targetRoleIds.contains(roleId)) {
				userRoleMapper.remove(userId, roleId);
			}
		}
		/**
		 * 是否添加
		 */
		for (Long roleId : targetRoleIds) {
			if (!originRoleIds.contains(roleId)) {
				UserRole userRole = this.makeUserRole(userId, roleId);
				userRoleMapper.save(userRole);
			}
		}
	}

	/**
	 * 构造用户角色 .
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	private UserRole makeUserRole(Long userId, Long roleId) {
		UserRole userRole = new UserRole();
		userRole.setUserId(userId);
		userRole.setRoleId(roleId);
		userRole.setCreateUser(SessionUtils.getCurrentUserName());
		return userRole;
	}

	@Override
	public void saveLoginTime(User user) {
		logger.debug("user{}" + JSONMapper.getInstance().toJson(user));
		user.setVisitCount(this.increaseTimes(user));
		user.setLastVisitTime(DateUtils.getSysDate());
		userMapper.change(user);
	}

	/**
	 * 累加登录次数.
	 * 
	 * @param user
	 * @return
	 */
	private Integer increaseTimes(User user) {
		int times;
		if (null == user.getVisitCount()) {
			times = 0;
		} else {
			times = user.getVisitCount();
		}
		times++;
		return times;
	}

	/**
	 * 验证原密码是否正确
	 * 
	 * @param user
	 * @param oldPwd
	 * @return
	 */
	public boolean checkPassword(User user, String oldPassword) {
		logger.debug("oldPassword:" + user.getPassword() + " pass:" + MD5Utils.getStringMD5(oldPassword));
		if (user.getPassword().equals(MD5Utils.getStringMD5(oldPassword))) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.lanwon.wechart.system.service.UserService#query(com.lanwon.wechart.system.entity.User)
	 */
	@Override
	public List<User> query(User entity) {
		return userMapper.query(entity);
	}

}
