package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	
	private final ItemService service;
	
	@GetMapping("/admin")
	public String adminTop(Model model) {
		return "admin/adminTop";
	}
	
	@GetMapping("/admin/itemlist")
	public String list(Model model) throws Exception {
		model.addAttribute("items", service.getItemList());
		return "admin/itemlist";
	}
}
