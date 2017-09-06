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
public class LogActor implements Serializable {
	
	private static final long serialVersionUID = 735010689008147706L;
	private String ipAddr;
	private String actor;

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

}
