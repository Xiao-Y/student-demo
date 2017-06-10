package org.billow.controller.home;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.billow.api.leave.LeaveService;
import org.billow.api.menu.MenuService;
import org.billow.model.domain.MenuBase;
import org.billow.model.expand.MenuDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.RequestUtils;
import org.billow.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home")
public class HomeController implements Comparator<MenuBase> {

	@Autowired
	private MenuService menuService;

	@Autowired
	private LeaveService leaveService;

	/**
	 * 登陆
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月24日 下午10:32:24
	 */
	@RequestMapping("/login")
	public String login() {
		return "page/home/login";
	}

	/**
	 * 进入主页
	 * 
	 * @param user
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月24日 下午10:32:33
	 */
	@RequestMapping("/homeIndex")
	public String homeIndex(UserDto user) {
		HttpSession session = RequestUtils.getRequest().getSession();
		if (ToolsUtils.isEmpty(user.getUserName())) {
			user = new UserDto();
			user.setUserName("employee");
			user.setUserId(1);
		}
		session.setAttribute("currentUser", user);
		return "page/home/index";
	}

	@RequestMapping("/main")
	public String main() {

		return "page/home/main";
	}

	/**
	 * 显示菜单
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年4月24日 上午9:45:13
	 */
	@ResponseBody
	@RequestMapping("/menu")
	public List<MenuDto> index(HttpServletRequest request) {
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
		}
		return selectAll;
	}

	/**
	 * 菜单排序
	 */
	@Override
	public int compare(MenuBase m1, MenuBase m2) {
		return m1.getDisplayno().compareTo(m2.getDisplayno());
	}
}
