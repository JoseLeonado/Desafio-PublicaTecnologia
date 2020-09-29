package com.joseleonardo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	/* Redireciona para nossa página principal que é o menu */
	@GetMapping("/menu")
	public String index() {
		return "index";
	}

}
