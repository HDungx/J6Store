package com.j6Store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
	
	@RequestMapping("/product/list")
	public String list() {
		return "product/list";
	}
	@RequestMapping("/product/detail/{id}")
	public String detail() {
		return "product/detail";
	}
}
