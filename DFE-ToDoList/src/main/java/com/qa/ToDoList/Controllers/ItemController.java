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
	public String create(@RequestBody Item item) {
		service.create(item);
		return "Item Added";
	}
	
	@GetMapping("/readAllItem")
	public List<ItemDTO> readAll() {
	    return service.readAll();
	}
	
	@GetMapping("/readAllItemByUser/{id}")
	public List<ItemDTO> readAllByUser(@PathVariable long id) {
	    return service.readAllByUser(id);
	}
	
	@GetMapping("/readAllByStatus/{state}")
	public List<ItemDTO> readAllByUser(@PathVariable Status state) {
	    return service.readAllByStatus(state);
	}
}
