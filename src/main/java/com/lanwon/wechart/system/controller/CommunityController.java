package com.lanwon.wechart.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanwon.common.page.PageView;
import com.lanwon.common.util.NumberUtils;
import com.lanwon.wechart.base.controller.BaseController;
import com.lanwon.wechart.base.entity.DataGrid;
import com.lanwon.wechart.system.entity.Community;
import com.lanwon.wechart.system.service.CommunityService;


/**
 * 
 * Function:小区信息管理控制器. <br/>
 * 
 * @date:2016年5月12日 上午11:18:52 <br/>
 * @author weiguo21
 * @version:
 * @since:JDK 1.7
 */
@Controller
@RequestMapping("template/community")
public class CommunityController extends BaseController {
	@Autowired
	private CommunityService communityService;
	@SuppressWarnings("unused")
	private static final Log logger = LogFactory.getLog(CommunityController.class);

	/**
	 * 默认页面
	 * 
	 * @throws DAOException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toCommunity() {
		// this.setDataSource();
		return "template/community_list";
	}

	/**
	 * 获取用户JSON数据.
	 */
	// @RequiresPermissions("sys:user:view")
	@RequestMapping(value = "json", method = RequestMethod.GET)
	@ResponseBody
	public DataGrid pageCommunity(Community query, HttpServletRequest request) {
		PageView<Community> page = communityService.pageCommunity(query);
		return super.buildDataGrid(page);
	}

	/**
	 * 加载保存小区页面.
	 * 
	 * @param model
	 */
	@RequestMapping(value = "save", method = RequestMethod.GET)
	public String toSaveCommunity(Model model) {
		model.addAttribute("community", new Community());
		model.addAttribute("action", "save");
		return "template/community_form";
	}

	/**
	 * 保存小区信息.
	 * 
	 * @param dict
	 * @param model
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String saveCommunity(@Valid Community community, Model model) {
		communityService.save(community);
		return "success";
	}

	/**
	 * 加载修改小区页面.
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "change/{id}", method = RequestMethod.GET)
	public String toChangeCommunity(@PathVariable("id") Long id, Model model) {
		model.addAttribute("community", communityService.getById(id));
		model.addAttribute("action", "change");
		return "template/community_form";
	}

	/**
	 * 修改小区信息.
	 * 
	 * @param dict
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "change", method = RequestMethod.POST)
	@ResponseBody
	public String changeCommunity(@Valid @ModelAttribute @RequestBody Community community, Model model) {
		communityService.save(community);
		return "success";
	}

	/**
	 * 删除小区信息.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "remove/{id}")
	@ResponseBody
	public String remove(@PathVariable("id") Long id) {
		if (!NumberUtils.isEmptyLong(id)) {
			communityService.remove(id);
		}
		return "success";
	}

}
