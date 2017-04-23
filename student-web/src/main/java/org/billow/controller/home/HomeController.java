package org.billow.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

	@RequestMapping("/homeIndex")
	public String homeIndex() {

		return "page/home/index";
	}

	@RequestMapping("/main")
	public String main() {

		return "page/home/main";
	}
}
