package com.lanwon.wechart.system.entity;

import java.util.Date;

/**
 * @author dzb	
 * @date 2017年9月6日 
 * @see  角色菜单表
 */
public class RoleMenu{
	
	private static final long serialVersionUID = 2869401164732841152L;
	/**
	 * 主键.
	 */
	private Long id;
	/**
	 * 角色id.
	 */
	private Long roleId;
	/**
	 * 权限id.
	 */
	private Long menuId;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 最后修改人
	 */
	private String modifyUser;
	/**
	 * 创建时间.
	 */
	private Date createTime;
	/**
	 * 最后修改时间.
	 */
	private Date modifyTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	
	/**
	 * @return the menuId
	 */
	public Long getMenuId() {
		return menuId;
	}
	/**
	 * @param menuId the menuId to set
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getModifyUser() {
		return modifyUser;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}


}
