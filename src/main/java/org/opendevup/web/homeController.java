package org.opendevup.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/welcome")
public class homeController {
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homeView() {
		return "home";
	}
	
}
