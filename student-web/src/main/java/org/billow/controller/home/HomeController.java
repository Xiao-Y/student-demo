package org.billow.controller.home;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.impl.identity.Authentication;
import org.billow.api.leave.LeaveService;
import org.billow.api.menu.MenuService;
import org.billow.api.user.UserService;
import org.billow.model.domain.MenuBase;
import org.billow.model.expand.MenuDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.HttpRequest;
import org.billow.utils.RequestUtils;
import org.billow.utils.ToolsUtils;
import org.billow.utils.generator.QrGenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/home")
public class HomeController implements Comparator<MenuBase> {

	@Value("${weichat.appid}")
	public String appid;
	@Value("${weichat.appsecret}")
	public String appsecret;
	@Value("${weichat.redirectUri}")
	public String redirectUri;
	@Autowired
	private MenuService menuService;
	@Autowired
	private LeaveService leaveService;
	@Autowired
	private UserService userService;

	private static final String UUID_MAP = "UUID_MAP";

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
	public String homeIndex(UserDto user, HttpServletRequest request) {
		HttpSession session = RequestUtils.getSession(request);

		String type = request.getParameter("type");
		if ("weichat".equals(type)) {// 从微信进入的
			UserDto currentUser = (UserDto) session.getAttribute("currentUser");
			BeanUtils.copyProperties(currentUser, user);
		}

		if (ToolsUtils.isEmpty(user.getUserName())) {
			user = new UserDto();
			user.setUserName("billow");
			user.setUserId(1);
		}
		session.setAttribute("currentUser", user);
		Authentication.setAuthenticatedUserId(user.getUserName());
		return "page/home/index";
	}

	/**
	 * 显示主页
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月18日 上午11:50:40
	 */
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

	/**
	 * 打开二维码显示页面
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月18日 上午11:31:55
	 */
	@RequestMapping("/showWeiChat")
	public String showWeiChat() {
		return "page/home/weichat";
	}

	/**
	 * 获取uuid及二维码图片地址
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/showQrGen")
	public void showQrGen(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 生成UUID随机数
		UUID randomUUID = UUID.randomUUID();
		// 通过应用获取共享的uuid集合
		Map<String, UserDto> map = (Map<String, UserDto>) req.getServletContext().getAttribute(UUID_MAP);
		if (map == null) {
			map = new HashMap<String, UserDto>();
			req.getServletContext().setAttribute(UUID_MAP, map);
		}
		// 把旧的uuid移除
		String uuid = req.getParameter("uuid");
		map.remove(uuid);
		// 把uuid放入map中
		map.put(randomUUID.toString(), null);
		// 二维码图片扫描后的链接
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri="
				+ redirectUri + "&response_type=code&scope=snsapi_userinfo&state=" + randomUUID + "#wechat_redirect";
		// 生成二维码图片
		ByteArrayOutputStream qrOut = QrGenUtil.createQrGen(url);
		String fileName = randomUUID + ".jpg";
		String tempPath = req.getServletContext().getRealPath("/temp");
		File file = new File(tempPath);
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		OutputStream os = new FileOutputStream(new File(tempPath, fileName));
		os.write(qrOut.toByteArray());
		os.flush();
		os.close();
		// 返回页面json字符串，uuid用于轮询检查时所带的参数， img用于页面图片显示
		String jsonStr = "{\"uuid\":\"" + randomUUID + "\",\"img\":\"" + "/temp/" + fileName + "\"}";
		OutputStream outStream = resp.getOutputStream();
		outStream.write(jsonStr.getBytes());
		outStream.flush();
		outStream.close();
	}

	/**
	 * 手机端扫描二维码执行的方法
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/loginByQrGen")
	protected void loginByQrGen(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		// 获取二维码链接中的uuid
		String uuid = req.getParameter("state");
		// 通过应用获取共享的uuid集合
		Map<String, UserDto> uuidMap = (Map<String, UserDto>) req.getServletContext().getAttribute(UUID_MAP);
		// 如果集合内没有这个uuid，则响应结果
		if (uuidMap == null || !uuidMap.containsKey(uuid)) {
			resp.getOutputStream().write("二维码不存在或已失效！".getBytes());
			return;
		}
		// 根据微信传来的code来获取用户的openID
		String code = req.getParameter("code");
		try {
			String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
			String param = "appid=" + appid + "&secret=" + appsecret + "&grant_type=authorization_code&code=" + code;
			Gson gson = new Gson();
			Map<String, String> map = gson.fromJson(HttpRequest.sendGet(url, param),
					new TypeToken<Map<String, String>>() {
					}.getType());
			Object openID = map.get("openid");
			if (openID != null && !"".equals(openID)) {
				// 通过openID获取user对象
				UserDto user = userService.getUserByOpenId(openID.toString());
				if (user != null) {
					// 如果查询到某个user拥有该openID，则设置到map集合内
					uuidMap.put(uuid, user);
					// 并返回手机端扫描结果
					resp.getOutputStream().write("登陆成功！".getBytes());
					return;
				}
			}
			// 如果没有openID参数，或查询不到openID对应的user对象，则移除该uuid，并响应结果
			uuidMap.remove(uuid);
			resp.getOutputStream().write("你还未绑定，请关注微信号并绑定账号！并使用微信客户端扫描！".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * PC端不断进行轮询检查的方法
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/checkScan")
	protected void checkScan(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取页面的uuid参数
		String uuid = req.getParameter("uuid");
		// 失效时间
		Integer count = Integer.valueOf(req.getParameter("count"));
		// 通过应用获取共享的uuid集合
		Map<String, UserDto> map = (Map<String, UserDto>) req.getServletContext().getAttribute(UUID_MAP);
		if (map != null) {
			// 移除失效的uuid
			if (count > 30) {
				map.remove(uuid);
				resp.getOutputStream().write("invalid".getBytes());
			}
			// 查询该uuid是否存在，且二维码已经被扫描并匹配到账号
			if (map.containsKey(uuid)) {
				UserDto user = (UserDto) map.get(uuid);
				if (user != null) {
					// 从集合中移除
					map.remove(uuid);
					// 设置登录信息
					req.getSession().setAttribute("currentUser", user);
					resp.getOutputStream().write("ok".getBytes());
				} else {
					resp.getOutputStream().write("native".getBytes());
				}
			}
		}
	}
}
