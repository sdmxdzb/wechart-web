package com.lanwon.wechart.system.service;

import java.util.List;

import com.lanwon.wechart.system.entity.City;
import com.lanwon.wechart.system.entity.Station;



public interface BasService {
	/**
	 * 加载城市
	 * 
	 * @return
	 */
	public List<City> getCitys();

	/**
	 * 加载配送站点
	 * 
	 * @return
	 */
	public List<Station> getStations();

}
