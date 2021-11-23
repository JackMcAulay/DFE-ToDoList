package com.qa.ToDoList.Services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.ToDoList.DTOs.ItemDTO;
import com.qa.ToDoList.Entities.Item;
import com.qa.ToDoList.Entities.User;
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
		User user = userRepo.findUserByID(item.getUserId());
		dto.setID(item.getID());
		dto.setItemAuthor(user.getFirstName() + " " + user.getLastName());
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
			itemDTOs.add(this.mapToDTO(item));
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
	
	public ItemDTO readById(long itemId) {
		return this.mapToDTO(itemRepo.findById(itemId).orElseThrow(EntityNotFoundException::new));
	}
	
	public List<ItemDTO> readAllByUser(long userId) {
		List<Item> items = itemRepo.findAllByUserId(userId);
		return this.listDTOs(items);
	}
	
	public List<ItemDTO> readAllByStatus(long userId, Status state){
		List<Item> items = itemRepo.findAllByUserIdAndStatus(userId, state);
		return this.listDTOs(items);
	}
	
	public ItemDTO completeItem(long itemId) {
		Item item = itemRepo.findById(itemId).orElseThrow(EntityNotFoundException::new);
		item.setStatus(Status.COMPLETED);
		itemRepo.save(item);
		return this.mapToDTO(item);
	}
	
	public void delete(long id) {
		itemRepo.deleteById(id);
	}
}
