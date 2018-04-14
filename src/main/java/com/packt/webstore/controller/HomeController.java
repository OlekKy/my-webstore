package com.packt.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.packt.webstore.exception.DatabaseException;
import com.packt.webstore.service.ProductServiceDb;

@Controller
public class HomeController {

	@Autowired
	ProductServiceDb productServiceDb;
	
	@RequestMapping("/")
	public String welcome(Model model) throws DatabaseException {
		model.addAttribute("greeting", "Witaj w sklepie internetowym!");
		model.addAttribute("tagline", "Wyjatkowym i jedynym sklepie internetowym");
		productServiceDb.createTable();
		return "welcome";
	}
	
	@RequestMapping("/welcome/greeting")
	public String greeting() {
		return "welcome";
	}
}
