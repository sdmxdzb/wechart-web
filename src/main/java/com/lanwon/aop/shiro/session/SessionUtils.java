package com.lanwon.aop.shiro.session;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.lanwon.aop.shiro.MyShiroRealm.ShiroUser;
import com.lanwon.wechart.system.entity.User;



/**
 * @see 登录用户相关数据. <br/>
 */
public class SessionUtils {
	/**
	 * 获取当前用户对象shiroUser.
	 * 
	 * @return
	 */
	public static ShiroUser getCurrentShiroUser() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}

	/**
	 * 获取当前用户session.
	 * 
	 * @return session
	 */
	public static Session getSession() {
		Session session = SecurityUtils.getSubject().getSession();
		return session;
	}

	/**
	 * 获取当前用户httpSession.
	 * 
	 * @return
	 */
	public static Session getHttpSession() {
		Session session = SecurityUtils.getSubject().getSession();
		return session;
	}

	/**
	 * 获取当前用户对象.
	 * 
	 * @return user
	 */
	public static User getCurrentUser() {
		Session session = SecurityUtils.getSubject().getSession();
		if (null != session) {
			return (User) session.getAttribute(SessionConstants.SESSION_USER_KEY);
		} else {
			return null;
		}
	}

	/**
	 * 获取当前登录用户id.
	 * 
	 * @return
	 */
	public static Long getCurrentUserId() {
		User user = getCurrentUser();
		if (user != null) {
			return user.getId();
		}
		return 1L;
	}

	/**
	 * 获取当前登录用户名.
	 * 
	 * @return
	 */
	public static String getCurrentUserName() {
		User user = getCurrentUser();
		if (user != null) {
			return user.getUsername();
		}
		return null;
	}
}
