package com.lanwon.wechart.system.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lanwon.common.page.PageQuery;
import com.lanwon.common.page.PageView;
import com.lanwon.wechart.system.entity.Role;
import com.lanwon.wechart.system.mapper.RoleMapper;
import com.lanwon.wechart.system.service.RoleService;


/**
 * @see 角色服务实现. 
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleMapper roleMapper;

	@Override
	public PageView<Role> pageRole(PageQuery query) {
		List<Role> list = this.buildPage(query);
		return new PageView<>(query, list);
	}

	private List<Role> buildPage(PageQuery query) {
		List<Role> list = roleMapper.page(query);
		int count = roleMapper.count(query);
		query.setItems(count);
		return list;
	}

	@Override
	public void saveRole(Role Role) {
		roleMapper.save(Role);
	}

	@Override
	public void changeRole(Role Role) {
		roleMapper.change(Role);
	}

	@Override
	public Role getRoleById(Long roleId) {
		return roleMapper.getById(roleId);
	}

	@Override
	public void removeRoleById(Long roleId) {
		roleMapper.remove(roleId);
	}

}
