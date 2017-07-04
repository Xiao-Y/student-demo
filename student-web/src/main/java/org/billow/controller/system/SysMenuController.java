package org.billow.controller.system;

import java.util.List;

import org.billow.api.menu.MenuService;
import org.billow.model.expand.MenuDto;
import org.billow.utils.PageHelper;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {

	@Autowired
	private MenuService menuService;

	/**
	 * 菜单管理
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年4月24日 上午9:52:46
	 */
	@RequestMapping("/menuManage")
	public ModelAndView menuManage(MenuDto menu) {
		ModelAndView av = new ModelAndView();
		PageHelper.startPage();
		List<MenuDto> menus = menuService.selectAll(menu);
		PageInfo<MenuDto> page = new PageInfo<>(menus);
		av.addObject("page", page);
		av.setViewName(PagePathCst.BASEPATH_SYSTEM + "menuManage");
		return av;
	}

	@RequestMapping("/menuEdit")
	public ModelAndView menuEdit(MenuDto menu) {
		ModelAndView av = new ModelAndView();
		MenuDto menuDto = new MenuDto();
		menuDto.setPid(0);
		List<MenuDto> pids = menuService.selectAll(menuDto);
		av.addObject("pids", pids);
		av.setViewName(PagePathCst.BASEPATH_SYSTEM + "menuEdit");
		return av;
	}

}
