package org.billow.vue.controller.system;

import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.billow.api.menu.MenuService;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.MenuDto;
import org.billow.utils.PageHelper;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("vue/sysMenu")
public class VueSysMenuController {

    private static final Logger logger = Logger.getLogger(VueSysMenuController.class);

    @Autowired
    private MenuService menuService;

    /**
     * 菜单管理
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @return
     * @date 2017年4月24日 上午9:52:46
     */
    @RequestMapping("/menuManage")
    public PageInfo<MenuDto> menuManage(MenuDto menu) {
        PageHelper.startPage();
        List<MenuDto> menus = menuService.selectAll(menu);
        PageInfo<MenuDto> page = new PageInfo<>(menus);
        return page;
    }

    @RequestMapping("/menuEdit")
    public ModelAndView menuEdit(MenuDto menu) {
        MenuDto menuDto = new MenuDto();
        // 编辑时，显示数据
        if (menu.getId() != null) {
            menuDto = menuService.selectByPrimaryKey(menu);
        }
        MenuDto menuDtoSelect = new MenuDto();
        menuDtoSelect.setPid(0);
        List<MenuDto> pids = menuService.selectAll(menuDtoSelect);
        ModelAndView av = new ModelAndView();
        // 用于修改后保持停留在页面
        menuDto.setPageNo(menu.getPageNo());
        av.addObject("menu", menuDto);
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
        json.setRoot("/sysMenu/menuManage?pageNo=" + menu.getPageNo());
        return json;
    }

    @ResponseBody
    @RequestMapping("/menuDel")
    public JsonResult menuDel(MenuDto menu) {
        JsonResult json = new JsonResult();
        boolean success = false;
        String message = "";
        try {
            menuService.deleteByPrimaryKey(menu);
            message = MessageTipsCst.DELETE_SUCCESS;
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.DELETE_FAILURE;
        }
        json.setMessage(message);
        json.setSuccess(success);
        return json;
    }
}
