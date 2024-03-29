package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.app.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TopController {

	private final ItemService service;

	@GetMapping
	public String ShowTop(Model model) throws Exception {
		model.addAttribute("items", service.getItemListPart());
		return "top";
	}

	@GetMapping("/top")
	public String JumpTop(Model model) throws Exception {
		model.addAttribute("items", service.getItemListPart());
		return "top";
	}

	@GetMapping("/itemlist")
	public String ShowItemlist(Model model) throws Exception {
		model.addAttribute("items", service.getItemList());
		return "itemlist";
	}

}
