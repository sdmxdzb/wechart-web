/**
 * 蓝网科技股份有限公司
 */
package com.lanwon.wechart.base.entity;

import java.io.Serializable;

/**
 * @author dzb	
 * @date 2017年9月6日 
 * 
 */
public class CodeName implements Serializable{
	

	private static final long serialVersionUID = -2391785072806972075L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
