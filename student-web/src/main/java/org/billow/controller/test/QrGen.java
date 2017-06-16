package org.billow.controller.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.billow.api.user.UserService;
import org.billow.model.expand.UserDto;
import org.billow.utils.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/QrGen")
public class QrGen {

	// public static final String appid = "wx62c96fa78688299a";
	// public static final String appsecret =
	// "523ed37219fd29e999222a1f61ea80ac";
	// public static final String redirect_uri =
	// "http%3a%2f%2f10.11.246.139%3a8090%2fstudent-web%2fhome%2flogin";
	// ------------------------------------------//
	public static final String appid = "wxe92da7aab459b577";
	public static final String appsecret = "f8dd42dc18a52aed57127085c311582e";
	public static final String redirect_uri = "http%3a%2f%2fpre.fliplus.com%2fvp-web-buyer-wechat%2flogin.jhtml";

	// public static final String appid = "wxe92da7aab459b577";
	// public static final String appsecret =
	// "f8dd42dc18a52aed57127085c311582e";
	// public static final String redirect_uri =
	// "http%3a%2f%2fpre.fliplus.com%2fvp-web-buyer-wechat%2flogin.jhtml";
	// ------------------------------------------//

	private UserService userService;

	@RequestMapping("/index")
	public String index() {
		return "index";
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
		Map<String, UserDto> map = (Map<String, UserDto>) req.getServletContext().getAttribute("UUID_MAP");
		if (map == null) {
			map = new HashMap<String, UserDto>();
			req.getServletContext().setAttribute("UUID_MAP", map);
		}
		// 把uuid放入map中
		map.put(randomUUID.toString(), null);

		// 二维码图片扫描后的链接
		// String url =
		// "http://10.11.246.139:8090/student-web/QrGen/loginByQrGen?uuid=" +
		// randomUUID;
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid + "&redirect_uri=" + redirect_uri
				+ "&response_type=code&scope=snsapi_userinfo&state=" + randomUUID + "#wechat_redirect";

		// 生成二维码图片
		ByteArrayOutputStream qrOut = QrGenUtil.createQrGen(url);
		String fileName = randomUUID + ".jpg";
		OutputStream os = new FileOutputStream(new File(req.getServletContext().getRealPath("/temp"), fileName));
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
	 * 跳转到“http://pre.fliplus.com/vp-web-buyer-wechat/login.jhtml”的后台方法
	 * 比如loginByQrGen，在这个里面主要是为了获取code和openid，使用openid到后台去查询这个用户是否存在
	 * 如果存在则进去系统页面，跳转页面绑定（我这个地方没你们的登陆做不了）
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/loginByQrGen")
	protected void loginByQrGen(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取二维码链接中的uuid
		String uuid = req.getParameter("uuid");
		// 通过应用获取共享的uuid集合
		Map<String, UserDto> uuidMap = (Map<String, UserDto>) req.getServletContext().getAttribute("UUID_MAP");
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
			Map map = gson.fromJson(HttpRequest.sendGet(url, param), new TypeToken<Map>() {
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
		// 通过应用获取共享的uuid集合
		Map<String, UserDto> map = (Map<String, UserDto>) req.getServletContext().getAttribute("UUID_MAP");
		if (map != null) {
			// 查询该uuid是否存在，且二维码已经被扫描并匹配到账号
			if (map.containsKey(uuid)) {
				UserDto user = (UserDto) map.get(uuid);
				if (user != null) {
					// 从集合中移除
					map.remove(uuid);
					// 设置登录信息
					req.getSession().setAttribute("USER_IN_SESSION", user);
					resp.getOutputStream().write("ok".getBytes());
				} else {
					resp.getOutputStream().write("native".getBytes());
				}
			}
		}
	}
}
