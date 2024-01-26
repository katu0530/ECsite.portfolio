package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.Admin;
import com.example.app.service.AdminService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService service;
	
	@GetMapping
	public String ShowAdminTop(Model model) {
		model.addAttribute("admin", new Admin());
		return "admin/admintop";
	}
	
	@PostMapping
	public String Adminlogin(@Valid Admin admin,
			Errors errors,
			HttpSession session) throws Exception {
		if(errors.hasErrors()) {
			return "admin/admintop";
		}
		
		if(!service.isCorrectIdAndPassword(admin.getLoginId(), admin.getLoginPass())) {
			errors.rejectValue("loginId", "error.incorrect_id_password");
			return "admin/admintop";
		}
		
		session.setAttribute("loginId", admin.getLoginId());
		return "redirect:/admin";
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "admin/logout";
	}

}
