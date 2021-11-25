package com.qa.ToDoList.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.ToDoList.DTOs.ItemDTO;
import com.qa.ToDoList.Entities.Item;
import com.qa.ToDoList.Enums.Status;
import com.qa.ToDoList.Services.ItemService;

@RestController
public class ItemController {

	ApplicationContext context;
	ItemService service;
	
	@Autowired
	public ItemController(ApplicationContext context, ItemService service) {
		this.context = context;
		this.service = service;
	}
	
	@PostMapping("/createItem")
	public ItemDTO create(@RequestBody Item item) {
		return service.create(item);
	}
	
	@GetMapping("/readAllItems")
	public List<ItemDTO> readAll() {
	    return service.readAll();
	}
	
	@GetMapping("/readItemById/{itemId}")
	public ItemDTO readItemById(@PathVariable long itemId) {
	    return service.readById(itemId);
	}
	
	@GetMapping("/readUserTDL/{userId}")
	public List<ItemDTO> readAllByUser(@PathVariable long userId) {
	    return service.readAllByUser(userId);
	}
	
	@GetMapping("/readUserTDLByStatus/{userId}/{state}")
	public List<ItemDTO> readAllByStatus(@PathVariable long userId, @PathVariable Status state) {
	    return service.readAllByStatus(userId, state);
	}
	
	@GetMapping("/readUserTDLByTag/{userId}/{tag}")
	public List<ItemDTO> readAllByStatus(@PathVariable long userId, @PathVariable String tag) {
	    return service.readAllByTag(userId, tag);
	}
	
	@PostMapping("/completeItem/{itemId}")
	public ItemDTO completeItem(@PathVariable long itemId) {
		return service.completeItem(itemId);
	}
	
	@PostMapping("/updateItem/{itemId}")
	public ItemDTO updateItem(@PathVariable long itemId, @RequestBody Item item) {
		service.delete(itemId);
		return service.create(item);
	}
	
	@PostMapping("/deleteItem/{itemId}")
	public String deleteItem(@PathVariable long itemId) {
		service.delete(itemId);
		return "Item " + itemId + " Deleted";
	}
	
	@PostMapping("/deleteCompleted/{userId}")
	public String deleteCompleted(@PathVariable long userId) {
		service.deleteByStatus(userId, Status.COMPLETED);
		return "Complted Items Deleted";
	}
	
	@PostMapping("/deleteLate/{userId}")
	public String deleteLate(@PathVariable long userId) {
		service.deleteByStatus(userId, Status.LATE);
		return "Late Items Deleted";
	}
}
