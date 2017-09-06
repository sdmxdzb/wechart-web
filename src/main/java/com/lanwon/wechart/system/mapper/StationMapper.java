package com.lanwon.wechart.system.mapper;

import java.util.List;

import com.lanwon.wechart.base.dao.BaseMapper;
import com.lanwon.wechart.system.entity.Station;


public interface StationMapper  extends BaseMapper<Station>{

	public List<Station> getValidStations();

}
