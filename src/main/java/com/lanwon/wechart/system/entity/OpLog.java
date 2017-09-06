package com.lanwon.wechart.system.entity;

import java.io.Serializable;
import java.util.Date;

import com.lanwon.wechart.base.entity.LogActor;


/**
 * @author dzb	
 * @date 2017年9月6日 
 * 
 */
public class OpLog extends LogActor implements Serializable {
	
	private static final long serialVersionUID = 279409227848908160L;
	private Long id;
	/**
	 * 子系统
	 */
	private String subSystem;
	/**
	 * 操作模块名称
	 */
	private String module;

	/**
	 * 操作动作的名称
	 */
	private String operation;

	/**
	 * 参数
	 */
	private String content;
	/**
	 * 操作描述
	 */
	private String description;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubSystem() {
		return subSystem;
	}

	public void setSubSystem(String subSystem) {
		this.subSystem = subSystem;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
