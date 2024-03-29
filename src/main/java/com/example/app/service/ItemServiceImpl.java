package com.example.app.service;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.domain.Item;
import com.example.app.mapper.ItemMapper;
import com.example.app.mapper.TopMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

	private final ItemMapper itemMapper;
	private final TopMapper topMapper;

	@Override
	public List<Item> getItemList() throws Exception {
		return itemMapper.selectAll();
	}

	@Override
	public Item getItemById(Integer id) throws Exception {
		return itemMapper.selectById(id);
	}

	@Override
	public void addItem(Item item) throws Exception {
		// 画像が選択されている場合の処理
		MultipartFile upfile = item.getUpfile();
		if (!upfile.isEmpty()) {
			String photo = upfile.getOriginalFilename();
			// 格納するための画像名をセット
			item.setPhoto(photo);
			// 画像ファイルの保存
			ResourceBundle bundle = ResourceBundle.getBundle("application");
			File dest = new File(bundle.getString("upload.path") + photo);
			upfile.transferTo(dest);
		}
		
		itemMapper.insert(item);
	}

	@Override
	public void editItem(Item item) throws Exception {
		itemMapper.update(item);
	}

	@Override
	public void deleteItem(Integer id) throws Exception {
		itemMapper.delete(id);
	}

	@Override
	public List<Item> getItemListPart() throws Exception {
		return topMapper.selectPart();
	}

}
