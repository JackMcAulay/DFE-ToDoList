package com.qa.ToDoList.Services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.ToDoList.Entities.User;
import com.qa.ToDoList.Entities.UserRepository;

@Service
public class UserService {

	private UserRepository repo;
	
	@Autowired
	public UserService(UserRepository repo) {
		super();
		this.repo = repo;
	}
	
	public void create(User user) {
		repo.save(user);
	}
	
	public void createMultiple(List<User> userList) {
		for (User user: userList) {
			repo.save(user);
		}
	}
	
	public List<User> readAll(){
		return repo.findAll();
	}
	
	public User readById(long id) {
		return repo.findById(id).orElseThrow(EntityNotFoundException::new);
	}
	
	public void delete(User u) {
		repo.deleteById(u.getID());
	}
}
