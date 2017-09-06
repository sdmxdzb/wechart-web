package com.lanwon.wechart.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.lanwon.common.page.PageQuery;
import com.lanwon.common.page.PageView;
import com.lanwon.wechart.system.entity.Community;
import com.lanwon.wechart.system.mapper.CommunityMapper;
import com.lanwon.wechart.system.service.CommunityService;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CommunityServiceImpl implements CommunityService {
	@Resource
	private CommunityMapper communityMapper;

	@Override
	public PageView<Community> pageCommunity(PageQuery query) {
		query.setPageSize(query.getPageSize());
		List<Community> list = communityMapper.page(query);
		int count = communityMapper.count(query);
		query.setItems(count);
		return new PageView<Community>(query, list);
	}

	@Override
	public void save(Community community) {
		communityMapper.save(community);
	}

	@Override
	public void remove(Long id) {
		communityMapper.remove(id);
	}

	@Override
	public Community getById(Long id) {
		return communityMapper.getById(id);
	}

}
