package com.qa.ToDoList.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.ToDoList.DTOs.ItemDTO;
import com.qa.ToDoList.Entities.Item;
import com.qa.ToDoList.Entities.ItemRepository;
import com.qa.ToDoList.Entities.UserRepository;
import com.qa.ToDoList.Enums.Status;

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
		dto.setID(item.getID());
		dto.setItemAuthor(userRepo.findUserByID(item.getUserId()).getName());
		dto.setItemTitle(item.getItemTitle());
		dto.setItemContents(item.getItemContents());
		dto.setDateCreated(item.getDateCreated());
		dto.setDateDue(item.getDateDue());
		dto.setStatus(item.getStatus());
		return dto;
	}
	
	public List<ItemDTO> listDTOs (List<Item> items) {
		List<ItemDTO> itemDTOs = new ArrayList<ItemDTO>();
		for (Item item : items) {
			itemDTOs.add(mapToDTO(item));
			System.out.println(item);
		}
		return itemDTOs;
	}
	
	public ItemDTO create(Item item) {
		itemRepo.save(item);
		return this.mapToDTO(item);
	}
	
	public List<ItemDTO> readAll() {
		List<Item> items = itemRepo.findAll();
		return listDTOs(items);
	}
	
	public List<ItemDTO> readAllByUser(long userId) {
		List<Item> items = itemRepo.findAllByUserId(userId);
		return listDTOs(items);
	}
	
	public List<ItemDTO> readAllByStatus(Status state){
		List<Item> items = itemRepo.findAllByStatus(state);
		return listDTOs(items);
	}
	
	public void delete(long id) {
		itemRepo.deleteById(id);
	}
}
