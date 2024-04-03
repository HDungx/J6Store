package com.j6Store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping({ "/", "/home/index" })
	public String Home() {
		return "redirect:/product/list";
	}

	@RequestMapping({ "/admin", "/admin/home/index" })
	public String Admin() {
		return "redirect:/assets/admin/index.html";
	}
}
