package org.billow.controller.home;

import com.alibaba.fastjson.JSONObject;
import org.billow.api.menu.MenuService;
import org.billow.model.domain.MenuBase;
import org.billow.model.expand.MenuDto;
import org.billow.utils.ToolsUtils;
import org.billow.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * 首页
 *
 * @author liuyongtao
 * @create 2018-01-12 11:23
 */
@RestController
@RequestMapping("/homeController")
public class HomeContrller implements Comparator<MenuBase> {

    @Autowired
    private MenuService menuService;

    /**
     * 显示菜单
     * <p>
     * <br>
     * added by liuyongtao<br>
     *
     * @return
     * @date 2017年4月24日 上午9:45:13
     */
    @RequestMapping("/menu")
    public List<MenuDto> getMenu(HttpServletRequest request) {
        try {
            String selectAllJson = RedisUtil.get("menu");
            if (ToolsUtils.isNotEmpty(selectAllJson)) {
                return JSONObject.parseArray(selectAllJson, MenuDto.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ServletContext servletContext = request.getServletContext();
        String contextPath = servletContext.getContextPath();
        MenuDto menu = new MenuDto();
        menu.setPid(0);
        List<MenuDto> selectAll = menuService.selectAll(menu);
        Collections.sort(selectAll, this);
        if (ToolsUtils.isNotEmpty(selectAll)) {
            for (MenuBase temp : selectAll) {
                List<MenuDto> childList = menuService.getMenuChildList(temp.getId());
                if (ToolsUtils.isNotEmpty(childList)) {
                    Iterator<MenuDto> iterator = childList.iterator();
                    while (iterator.hasNext()) {
                        MenuBase tempChild = iterator.next();
                        if (Long.compare(0, tempChild.getPid()) == 0) {
                            iterator.remove();
                        }
                        String href = tempChild.getHref();
                        if (ToolsUtils.isNotEmpty(href) && !(href.startsWith("https") || href.startsWith("http"))) {
                            href = contextPath + href;
                        }
                        tempChild.setHref(href);
                    }
                }
                Collections.sort(childList, this);
                temp.setChildren(childList);
            }
            try {
                RedisUtil.set("menu", JSONObject.toJSONString(selectAll));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return selectAll;
    }

    /**
     * 构建菜单路由
     *
     * @param request
     * @return
     */
    @RequestMapping("/getMenuRouter")
    public List<MenuDto> getMenuRouter(HttpServletRequest request) {
        return null;
    }

    @Override
    public int compare(MenuBase m1, MenuBase m2) {
        return m1.getDisplayno().compareTo(m2.getDisplayno());
    }
}
