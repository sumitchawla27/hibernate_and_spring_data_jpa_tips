package com.example.springdatajpatips.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {


	@Value("${springfox.documentation.swagger.v2.path}")
	private String swaggerPath;

	@RequestMapping("/swagger")
	protected String redirect() {
		return "redirect:/swagger/index.html?url=" + swaggerPath;
	}

}
