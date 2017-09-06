package com.lanwon.wechart.system.service;


import com.lanwon.common.page.PageQuery;
import com.lanwon.common.page.PageView;
import com.lanwon.wechart.system.entity.Community;


public interface CommunityService {
	
	public PageView<Community> pageCommunity(PageQuery query);

	public void save(Community community);

	public void remove(Long id);

	public Community getById(Long id);

}
