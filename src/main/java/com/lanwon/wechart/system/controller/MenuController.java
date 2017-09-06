package com.lanwon.wechart.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lanwon.aop.shiro.session.SessionUtils;
import com.lanwon.common.util.NumberUtils;
import com.lanwon.wechart.base.controller.BaseController;
import com.lanwon.wechart.base.mapper.JSONMapper;
import com.lanwon.wechart.system.entity.Menu;
import com.lanwon.wechart.system.service.MenuService;



/**
 * 
 * Function:权限控制器. <br/>
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	private MenuService menuService;

	/**
	 * 默认页面.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list() {
		return "system/menu_list";
	}

	/**
	 * 菜单页面.
	 */
	@RequestMapping(value = "menu", method = RequestMethod.GET)
	public String toMenuList() {
		return "system/menu_list";
	}

	/**
	 * 全部菜单数据.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/menu/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Menu> getAllMenus() {
		List<Menu> menuList = menuService.getAllMenus();
		return menuList;
	}

	/**
	 * 全部权限数据.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Menu> getAll() {
		List<Menu> menuList = menuService.getAllMenus();
		return menuList;
	}

	/**
	 * 获取菜单下的操作
	 */
	@RequestMapping("/operation/json")
	@ResponseBody
	public Map<String, Object> getMenuOperations(Long parentId) {
		logger.debug("parentId:" + parentId);
		Map<String, Object> map = new HashMap<String, Object>();
		if (!NumberUtils.isEmptyLong(parentId)) {
			List<Menu> menuOperList = menuService.getMenuOperations(parentId);
			map.put("rows", menuOperList);
			map.put("total", menuOperList.size());
		}
		return map;
	}

	/**
	 * 当前登录用户的权限集合.
	 */
	@RequestMapping("/i/json")
	@ResponseBody
	public List<Menu> geCurrentMenus() {
		List<Menu> menuList = menuService.getMenusByUserId(SessionUtils.getCurrentUserId());
		return menuList;
	}

	/**
	 * 某用户的权限集合.
	 */
	@RequestMapping("/{userId}/json")
	@ResponseBody
	public List<Menu> getUserMenus(@PathVariable("userId") Long userId) {
		if (NumberUtils.isEmptyLong(userId)) {
			return null;
		}
		List<Menu> menuList = menuService.getMenusByUserId(userId);
		return menuList;
	}

	/**
	 * 添加权限跳转
	 */
	@RequestMapping(value = "/toSave", method = RequestMethod.GET)
	public String toSave(Model model) {
		model.addAttribute("menu", new Menu());
		model.addAttribute("action", "save");
		return "system/menu_form";
	}

	/**
	 * 添加菜单跳转
	 */
	@RequestMapping(value = "/menu/save", method = RequestMethod.GET)
	public String toSaveMenu(Model model) {
		model.addAttribute("menu", new Menu());
		model.addAttribute("action", "save");
		return "system/menu_form";
	}

	/**
	 * 添加权限/菜单
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@Valid Menu menu, Model model) {
		logger.debug("menu:" + JSONMapper.getInstance().toJson(menu));
		menuService.save(menu);
		return "success";
	}

	/**
	 * 修改权限跳转
	 */
	@RequestMapping(value = "/change/{id}", method = RequestMethod.GET)
	public String toChangePerm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("menu", menuService.getById(id));
		model.addAttribute("action", "change");
		return "system/menu_form";
	}

	/**
	 * 修改菜单跳转
	 */
	@RequestMapping(value = "/menu/change/{id}", method = RequestMethod.GET)
	public String toChangeMenu(@PathVariable("id") Long id, Model model) {
		model.addAttribute("menu", menuService.getById(id));
		model.addAttribute("action", "change");
		return "system/menu_form";
	}

	/**
	 * 修改权限/菜单
	 */
	@RequestMapping(value = "/change", method = RequestMethod.POST)
	@ResponseBody
	public String change(@Valid @ModelAttribute("menu") Menu menu, Model model) {
		logger.info("change menu:" + JSONMapper.getInstance().toJson(menu));
		menuService.change(menu);
		return "success";
	}

	/**
	 * 删除权限
	 */
	@RequestMapping(value = "/remove/{id}")
	@ResponseBody
	public String remove(@PathVariable("id") Long id) {
		 menuService.removeById(id);
		 return "success";
	}

	@ModelAttribute
	public void getMenu(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("menu", menuService.getById(id));
		}
	}
}
