package com.lanwon.wechart.system.entity;

import com.lanwon.common.page.PageQuery;

/**
 * @author dzb	
 * @date 2017年9月6日 
 * 
 */
public class Community extends PageQuery {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private String cityName;

	private String cityCode;

	private String areaName;

	private String areaCode;

	private String community;

	private String company;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "CommunityDO [id=" + id + ", cityName=" + cityName + ", cityCode=" + cityCode + ", areaName=" + areaName
		      + ", areaCode=" + areaCode + ", community=" + community + ", company=" + company + "]";
	}
	

}