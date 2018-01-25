package org.billow.controller.system;

import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.billow.api.menu.MenuService;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.MenuDto;
import org.billow.model.translate.MenuSpreadEunm;
import org.billow.model.translate.MenuValidindEunm;
import org.billow.utils.PageHelper;
import org.billow.utils.ToolsUtils;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    private static final Logger logger = Logger.getLogger(SysMenuController.class);

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
    public PageInfo<MenuDto> menuManage(@RequestBody MenuDto menu) {
        PageHelper.startPage();
        List<MenuDto> menus = menuService.selectAll(menu);
        if (ToolsUtils.isNotEmpty(menus)) {
            for (MenuDto menuDto : menus) {
                menuDto.setSpreadName(MenuSpreadEunm.SPREAD_FALSE.getCodeName());
                if (menuDto.getSpread() != null && menuDto.getSpread()) {
                    menuDto.setSpreadName(MenuSpreadEunm.SPREAD_TRUE.getCodeName());
                }
                menuDto.setValidindName(MenuValidindEunm.VALIDIND_FALSE.getCodeName());
                if (menuDto.getValidind() != null && menuDto.getValidind()) {
                    menuDto.setValidindName(MenuValidindEunm.VALIDIND_TRUE.getCodeName());
                }
            }
        }
        PageInfo<MenuDto> page = new PageInfo<>(menus);
        return page;
    }

    @RequestMapping("/menuEdit")
    public JsonResult menuEdit(MenuDto menu) {
        MenuDto menuDto = new MenuDto();
        // 编辑时，显示数据
        if (menu.getId() != null) {
            menuDto = menuService.selectByPrimaryKey(menu);
        }
        MenuDto menuDtoSelect = new MenuDto();
        menuDtoSelect.setPid(0);
        List<MenuDto> pids = menuService.selectAll(menuDtoSelect);
        MenuDto menuTemp = new MenuDto();
        menuTemp.setId(0);
        menuTemp.setMenucode("0");
        menuTemp.setTitle("父级菜单");
        pids.add(0, menuTemp);
        JsonResult json = new JsonResult();
        json.addData("menu", menuDto).addData("pidMenu", pids);
        return json;
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
