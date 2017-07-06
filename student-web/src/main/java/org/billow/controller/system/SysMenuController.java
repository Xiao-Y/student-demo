package org.billow.controller.system;

import java.util.List;

import org.apache.log4j.Logger;
import org.billow.api.menu.MenuService;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.MenuDto;
import org.billow.utils.PageHelper;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {

	private static final Logger logger = Logger.getLogger(SysMenuController.class);

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
		// 编辑时，显示数据
		if (menu.getId() != null) {
			MenuDto menuDto = menuService.selectByPrimaryKey(menu);
			av.addObject("menu", menuDto);
		}
		MenuDto menuDto = new MenuDto();
		menuDto.setPid(0);
		List<MenuDto> pids = menuService.selectAll(menuDto);
		av.addObject("pids", pids);
		av.setViewName(PagePathCst.BASEPATH_SYSTEM + "menuEdit");
		return av;
	}

	@ResponseBody
	@RequestMapping("/menuSave")
	public JsonResult menuSave(MenuDto menu) {
		JsonResult json = new JsonResult();
		String message = "";
		String type = "";
		try {
			if (menu.getId() == null) {
				menuService.insert(menu);
			} else {
				menuService.updateByPrimaryKeySelective(menu);
			}
			message = MessageTipsCst.SUBMIT_SUCCESS;
			type = MessageTipsCst.TYPE_SUCCES;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			message = MessageTipsCst.SUBMIT_FAILURE;
			type = MessageTipsCst.TYPE_ERROR;
		}
		json.setMessage(message);
		json.setType(type);
		json.setRoot("/sysMenu/menuManage");
		return json;
	}

	@ResponseBody
	@RequestMapping("/menuDel")
	public JsonResult menuDel(MenuDto menu) {
		JsonResult json = new JsonResult();
		String message = "";
		String type = "";
		try {
			menuService.deleteByPrimaryKey(menu);
			message = MessageTipsCst.DELETE_SUCCESS;
			type = MessageTipsCst.TYPE_SUCCES;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			message = MessageTipsCst.DELETE_FAILURE;
			type = MessageTipsCst.TYPE_ERROR;
		}
		json.setMessage(message);
		json.setType(type);
		json.setRoot("/sysMenu/menuManage");
		return json;
	}
}
