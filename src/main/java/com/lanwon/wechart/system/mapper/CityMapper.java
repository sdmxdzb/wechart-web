package com.lanwon.wechart.system.mapper;

import java.util.List;
import com.lanwon.wechart.base.dao.BaseMapper;
import com.lanwon.wechart.system.entity.City;

public interface CityMapper extends BaseMapper<City> {
	public List<City> getValidCitys();
}
