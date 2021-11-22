package com.qa.ToDoList.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.ToDoList.Entities.User;
import com.qa.ToDoList.Services.UserService;

@RestController
public class UserController {

	ApplicationContext context;
	UserService service;
	
	@Autowired
	public UserController(ApplicationContext context, UserService service) {
		this.context = context;
		this.service = service;
	}
	
	@PostMapping("/createUser")
	public String create(@RequestBody User user) {
		service.create(user);
		return "User Added";
	}
	
	@PostMapping("/createUsers")
	public String createMultiple(@RequestBody List<User> uList) {
		service.createMultiple(uList);
		return "Users Added";
	}
	
	@GetMapping("/readAll")
	public List<User> readAll() {
	    return service.readAll();
	}
	
	@GetMapping("/readById/{id}")
	public User readById(@PathVariable long id){
		return service.readById(id);
	}
	
	@PostMapping("update/{id}")
	public String update(@PathVariable long id, @RequestBody User u) {
		service.delete(service.readById(id));
		service.create(u);
		return "User Updated";
	}
	
	@PostMapping("delete/{id}")
	public String delete(@PathVariable long id) {
		service.delete(service.readById(id));
		return "User Deleted";
	}
}
