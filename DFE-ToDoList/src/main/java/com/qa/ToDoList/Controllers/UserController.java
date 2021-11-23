package com.qa.ToDoList.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.ToDoList.DTOs.UserDTO;
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
	public UserDTO create(@RequestBody User user) {
		return service.create(user);
	}
	
	@PostMapping("/createUsers")
	public List<UserDTO> createMultiple(@RequestBody List<User> uList) {
		return service.createMultiple(uList);
	}
	
	@GetMapping("/readAllUsers")
	public List<UserDTO> readAll() {
	    return service.readAll();
	}
	
	@GetMapping("/readUserById/{userId}")
	public UserDTO readById(@PathVariable long userId){
		return service.readById(userId);
	}
	
	@PostMapping("updateUser/{userId}")
	public UserDTO update(@PathVariable long userId, @RequestBody User user) {
		return service.update(userId, user);
	}
	
	@PostMapping("deleteUser/{userId}")
	public String delete(@PathVariable long userId) {
		service.delete(userId);
		return "User Deleted";
	}
}
