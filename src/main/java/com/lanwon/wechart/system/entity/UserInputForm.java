package com.lanwon.wechart.system.entity;

import java.io.Serializable;
import java.util.Date;



public class UserInputForm implements Serializable {
	private static final long serialVersionUID = -529673548058093393L;

	public UserInputForm() {
		super();
	}

	public UserInputForm(User user) {
		super();
		this.user = user;
	}

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return user.getId();
	}

	public void setId(Long id) {
		user.setId(id);
	}

	public String getUsername() {
		return user.getUsername();
	}

	public void setUsername(String username) {
		user.setUsername(username);
	}

	public String getPassword() {
		return user.getPassword();
	}

	public void setPassword(String password) {
		user.setPassword(password);
	}

	public int hashCode() {
		return user.hashCode();
	}

	public String getName() {
		return user.getName();
	}

	public void setName(String name) {
		user.setName(name);
	}

	public Date getBirthday() {
		return user.getBirthday();
	}

	public void setBirthday(Date birthday) {
		user.setBirthday(birthday);
	}

	public Integer getGender() {
		return user.getGender();
	}

	public void setGender(Integer gender) {
		user.setGender(gender);
	}

	public String getEmail() {
		return user.getEmail();
	}

	public void setEmail(String email) {
		user.setEmail(email);
	}

	public String getMobile() {
		return user.getMobile();
	}

	public void setMobile(String mobile) {
		user.setMobile(mobile);
	}

	public String getDescription() {
		return user.getDescription();
	}

	public void setDescription(String description) {
		user.setDescription(description);
	}

	public Integer getStatus() {
		return user.getStatus();
	}

	public void setStatus(Integer status) {
		user.setStatus(status);
	}

	public Integer getVisitCount() {
		return user.getVisitCount();
	}

	public void setVisitCount(Integer visitCount) {
		user.setVisitCount(visitCount);
	}

	public Date getLastVisitTime() {
		return user.getLastVisitTime();
	}

	public void setLastVisitTime(Date lastVisitTime) {
		user.setLastVisitTime(lastVisitTime);
	}

	public Date getCreateTime() {
		return user.getCreateTime();
	}

	public void setCreateTime(Date createTime) {
		user.setCreateTime(createTime);
	}

	public Date getModifyTime() {
		return user.getModifyTime();
	}

	public void setModifyTime(Date modifyTime) {
		user.setModifyTime(modifyTime);
	}

	public boolean equals(Object obj) {
		return user.equals(obj);
	}

	public String toString() {
		return user.toString();
	}

}
