package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.User;
import com.example.app.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	
	@GetMapping
	public String ShowUserTop(Model model){
		model.addAttribute("user", new User());
		return "user/usertop";
	}
	
	@PostMapping
	public String UserLogin(@Valid User user, 
			Errors errors, 
			HttpSession session) throws Exception {
		if(errors.hasErrors()) {
			return "user/usertop";
		}
		
		if(!userService.isCorrectUserIdAndPassword(user.getLoginId(), user.getLoginPass())) {
			errors.rejectValue("userLoginId", "errors.incorrect_id_password");
			return "user/usertop";
		}
		session.setAttribute("userLoginId", user.getLoginId());
		session.setAttribute("userAll", userService.getUser());
		return "redirect:/user";
		
	}
	
	
	@GetMapping("/add")
	public String addGet(Model model, HttpSession session) {
		model.addAttribute("title", "会員登録");
		model.addAttribute("user", new User());
		session.setAttribute("userAll", userService.getUser());
		return "user/userinfo";
	}
	
	@PostMapping("/add")
	public String addPost(@Valid User user, Errors errors,
			RedirectAttributes rd, Model model) throws Exception {
		if(errors.hasErrors()) {
			model.addAttribute("title", "会員登録");
			return "user/userinfo";
		}
		userService.addUser(user);
		rd.addFlashAttribute("statusMessage", "会員の登録が完了しました。");
		return "redirect:/usertop";
	}
	
	@GetMapping("/edit")
	public String egitGet(Model model) throws Exception {
		model.addAttribute("title", "会員情報の変更");
		return "user/userinfo";
		
	}
	
	@PostMapping("/edit/{id}")
	public String editPst(@PathVariable Integer id, @Valid User user,
			Errors errors, RedirectAttributes rd, Model model) throws Exception {
		
		if(errors.hasErrors()) {
			model.addAttribute("title", "会員情報の追加");
			return "user/userinfo";
		}
		
		user.setId(id);
		userService.editUser(user);
		rd.addFlashAttribute("atstusMessage", "会員情報の変更が完了しました。");
		return "redirect;/userinfo";
	}
	
	@GetMapping("/logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "user/logout";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
