package com.lanwon.common.request;

import java.io.Serializable;

/**
 * @see  修改密码参数.
 */
public class PasswordRequest implements Serializable {
	
	private static final long serialVersionUID = -7725593337294109998L;
	private Long userId;
	private String originPassword;
	private String targetPassword;
	private String confirmPassword;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOriginPassword() {
		return originPassword;
	}

	public void setOriginPassword(String originPassword) {
		this.originPassword = originPassword;
	}

	public String getTargetPassword() {
		return targetPassword;
	}

	public void setTargetPassword(String targetPassword) {
		this.targetPassword = targetPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
