package com.lanwon.aop.shiro.session;

/**
 * 
 * @see 相关常量
 */
public class SessionConstants {
	/**
	 * 登录用户session key.
	 */
	public static final String SESSION_USER_KEY = "back_session_user";
	/**
	 * shiroUser session key.
	 */
	public static final String SHIRO_USER_KEY = "shiro_session_user";
	/**
	 * session过期时间.
	 */
	public static final long sessionTimeOut = 3600000;
}
