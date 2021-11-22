package com.qa.ToDoList.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.ToDoList.DTOs.ItemDTO;
import com.qa.ToDoList.Entities.Item;
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
	
	@PostMapping("/createNote")
	public String create(@RequestBody Item item) {
		service.create(item);
		return "User Added";
	}
	
	@GetMapping("/readAllNotes")
	public List<ItemDTO> readAll() {
	    return service.readAll();
	}
}
