package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.domain.Item;
import com.example.app.service.ItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ItemController {

	private final ItemService service;

	@GetMapping("/itemmanagement")
	public String list(Model model) throws Exception {
		model.addAttribute("items", service.getItemList());
		return "admin/itemmanagement";
	}

	@GetMapping("/add")
	public String addGet(Model model) {
		model.addAttribute("title", "商品の追加");
		model.addAttribute("item", new Item());
		return "admin/itemadd";
	}

	@PostMapping("/add")
	public String addPost(@Valid Item item,
			Errors errors,
			RedirectAttributes rd,
			Model model) throws Exception {

		MultipartFile upfile = item.getUpfile();
		if (!upfile.isEmpty()) {
			// 画像か否か判定する
			String type = upfile.getContentType();
			if (!type.startsWith("image/")) {
				// 画像ではない場合、エラーメッセージを表示
				errors.rejectValue("upfile", "error.not_image_file");
			}
		}

		if (errors.hasErrors()) {
			model.addAttribute("title", "商品の追加");
			return "admin/itemadd";
		}
		service.addItem(item);
		rd.addFlashAttribute("statusMessage", "会員を追加しました。");
		return "redirect:/admin/itemmanagement";

	}

	@GetMapping("/edit/{id}")
	public String editGet(@PathVariable Integer id, Model model) throws Exception {
		model.addAttribute("title", "商品情報の変更");
		model.addAttribute("item", service.getItemById(id));
		return "admin/itemadd";
	}

	@PostMapping("/edit/{id}")
	public String editPost(@PathVariable Integer id,
			@Valid Item item,
			Errors errors,
			RedirectAttributes rd,
			Model model) throws Exception {
		if (errors.hasErrors()) {
			model.addAttribute("title", "商品情報の変更");
			return "admin/itemadd";
		}
		item.setId(id);
		service.editItem(item);
		rd.addFlashAttribute("statusMessage", "会員情報を更新しました。");
		return "redirect:/admin/itemmanagement";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id, RedirectAttributes rd) throws Exception {
		service.deleteItem(id);
		rd.addFlashAttribute("statusMessage", "会員情報を削除しました。");
		return "redirect:/admin/itemmanagement";
	}

}
