package com.qa.ToDoList.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.ToDoList.DTOs.ItemDTO;
import com.qa.ToDoList.Entities.Item;
import com.qa.ToDoList.Entities.ItemRepository;
import com.qa.ToDoList.Entities.UserRepository;

@Service
public class ItemService {

	private UserRepository userRepo;
	private ItemRepository itemRepo;
	
	@Autowired
	public ItemService(UserRepository userRepo, ItemRepository itemRepo) {
		super();
		this.userRepo = userRepo;
		this.itemRepo = itemRepo;
	}
	
	public ItemDTO mapToDTO(Item item) {
		ItemDTO dto = new ItemDTO();
		dto.setItemAuthor(userRepo.findUserByID(item.getUserId()).getName());
		dto.setItemTitle(item.getItemTitle());
		dto.setItemContents(item.getItemContents());
		dto.setDateCreated(item.getDateCreated());
		dto.setDateDue(item.getDateDue());
		return dto;
	}
	
	public ItemDTO create(Item item) {
		itemRepo.save(item);
		return this.mapToDTO(item);
	}
	
	public List<ItemDTO> readAll() {
		List<Item> items = itemRepo.findAll();
		List<ItemDTO> itemDTOs = new ArrayList<ItemDTO>();
		for (Item item : items) {
			itemDTOs.add(mapToDTO(item));
		}
		return itemDTOs;
	}
}