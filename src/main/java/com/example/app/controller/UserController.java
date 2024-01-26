package com.example.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
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
	public String ShowUserTop(Model model,
			HttpSession session) {
		model.addAttribute("user", new User());
		String userLoginId = (String) session.getAttribute("usreLoginId");
		System.out.println("SESSION:" + userLoginId);
		return "user/usertop";
	}
	
	@PostMapping
	public String UserLogin(@Valid User user, 
			Errors errors, 
			HttpSession session) throws Exception {
		if(errors.hasErrors()) {
			// エラー内容の捕捉
			List<ObjectError> objList = errors.getAllErrors();
			for(ObjectError obj : objList) {
			System.out.println(obj.toString());
			}
			System.out.println(1);
			return "user/usertop";
		}
		
		if(!userService.isCorrectUserIdAndPassword(user.getUserLoginId(), user.getUserLoginPass())) {
			System.out.println(2);
			errors.rejectValue("userLoginId", "errors.incorrect_id_password");
			return "user/usertop";
		}
		session.setAttribute("usreLoginId", user.getUserLoginId());
		return "redirect:/user";
		
	}
	
	
	@GetMapping("/add")
	public String addGet(Model model) {
		model.addAttribute("title", "会員登録");
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
	
	@GetMapping("/edit/{id}")
	public String egitGet(@PathVariable Integer id, Model model) throws Exception {
		model.addAttribute("title", "会員情報の変更");
		model.addAttribute("userinfo", userService.getUserById(id));
		return "user/userinfo";
		
	}
	
	@PostMapping("/edit/{id}")
	public String editPst(@PathVariable Integer id, @Valid User user,
			Errors errors, RedirectAttributes rd, Model model) throws Exception {
		
		if(errors.hasErrors()) {
			model.addAttribute("title", "会員情報の追加");
			return "user/userinfo";
		}
		
		user.setUserId(id);
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
