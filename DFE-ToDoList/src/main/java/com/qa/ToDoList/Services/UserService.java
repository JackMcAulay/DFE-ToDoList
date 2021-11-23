package com.qa.ToDoList.Services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.ToDoList.DTOs.UserDTO;
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
	
	public UserDTO mapToDTO(User user) {
		UserDTO dto = new UserDTO();
		dto.setID(user.getID());
		dto.setFullName(user.getFirstName() + " " + user.getLastName());
		dto.setEmail(user.getEmail());
		return dto;
	}
	
	public List<UserDTO> listDTOs (List<User> users) {
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for (User user : users) {
			userDTOs.add(this.mapToDTO(user));
		}
		return userDTOs;
	}
	
	public UserDTO create(User user) {
		repo.save(user);
		return this.mapToDTO(user);
	}
	
	public List<UserDTO> createMultiple(List<User> userList) {
		for (User user: userList) {
			repo.save(user);
		}
		return this.listDTOs(userList);
	}
	
	public List<UserDTO> readAll(){
		return this.listDTOs(repo.findAll());
	}
	
	public UserDTO readById(long id) {
		return this.mapToDTO(repo.findById(id).orElseThrow(EntityNotFoundException::new));
	}
	
	public UserDTO update(long id, User updatedUser) {
		User user = repo.findById(id).orElseThrow(EntityNotFoundException::new);
		user.setFirstName(updatedUser.getFirstName());
		user.setLastName(updatedUser.getLastName());
		user.setEmail(updatedUser.getEmail());
		repo.save(user);
		return this.mapToDTO(user);
	}
	
	public void delete(long userId) {
		repo.deleteById(userId);
	}
}
