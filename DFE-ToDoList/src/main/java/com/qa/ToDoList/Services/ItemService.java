package com.qa.ToDoList.Services;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.ToDoList.DTOs.ItemDTO;
import com.qa.ToDoList.Entities.Item;
//import com.qa.ToDoList.Entities.User;
import com.qa.ToDoList.Entities.ItemRepository;
import com.qa.ToDoList.Entities.UserRepository;
import com.qa.ToDoList.Enums.Status;

@Service
public class ItemService {

	//private UserRepository userRepo;
	private ItemRepository itemRepo;
	
	@Autowired
	public ItemService(UserRepository userRepo, ItemRepository itemRepo) {
		super();
		//this.userRepo = userRepo;
		this.itemRepo = itemRepo;
	}
	
	public ItemDTO mapToDTO(Item item) {
		ItemDTO dto = new ItemDTO();
		//User user = userRepo.findUserByID(item.getUserId());
		dto.setID(item.getID());
		//dto.setItemAuthor(user.getFirstName() + " " + user.getLastName());
		dto.setItemTitle(item.getItemTitle());
		dto.setItemContents(item.getItemContents());
		dto.setDateCreated(item.getDateCreated());
		dto.setDateDue(item.getDateDue());
		dto.setStatus(item.getStatus());
		dto.setTags(item.getTags());
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
		this.updateStatus();
		List<Item> items = itemRepo.findAll();
		return listDTOs(items);
	}
	
	public ItemDTO readById(long itemId) {
		this.updateStatus();
		return this.mapToDTO(itemRepo.findById(itemId).orElseThrow(EntityNotFoundException::new));
	}
	
	public List<ItemDTO> readAllByUser(long userId) {
		this.updateStatus();
		List<Item> items = itemRepo.findAllByUserId(userId);
		return this.listDTOs(items);
	}
	
	public List<ItemDTO> readAllByStatus(long userId, Status state){
		this.updateStatus();
		List<Item> items = itemRepo.findAllByUserIdAndStatus(userId, state);
		return this.listDTOs(items);
	}
	
	public List<ItemDTO> readAllByTag(long userId, String tag){
		this.updateStatus();
		List<Item> items = new ArrayList<Item>();
		List<Item> allItems = itemRepo.findAllByUserId(userId);
		for (Item item : allItems) {
			System.out.println(item.getTags());
			if (item.getTags().contains(tag)) {
				items.add(item);
			}
		}
		return this.listDTOs(items);
	}
	
	public ItemDTO completeItem(long itemId) {
		Item item = itemRepo.findById(itemId).orElseThrow(EntityNotFoundException::new);
		item.setStatus(Status.COMPLETED);
		itemRepo.save(item);
		return this.mapToDTO(item);
	}
	
	public void updateStatus() {
		List<Item> items = itemRepo.findAllByStatus(Status.DUE);
		items.addAll(itemRepo.findAllByStatus(Status.DUETODAY));
		
		LocalDate localDate = LocalDate.now();
        Date currentDate = (Date) Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		
		for (Item item : items) {
			int dif = item.getDateDue().compareTo(currentDate);
            if (dif == 0 && item.getStatus() != Status.DUETODAY){
                item.setStatus(Status.DUETODAY);
                itemRepo.save(item);
            } else if (dif < 0){
            	item.setStatus(Status.LATE);
                itemRepo.save(item);
			}
		}
	}
	
	public void deleteByStatus(long userId, Status state) {
		List<Item> items = itemRepo.findAllByUserIdAndStatus(userId, state);
		for (Item item : items) {
			this.delete(item.getID());
		}
	}
	
	public void delete(long itemId) {
		itemRepo.deleteById(itemId);
	}
}
