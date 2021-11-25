package com.qa.ToDoList.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.ToDoList.DTOs.UserDTO;
import com.qa.ToDoList.Entities.User;
import com.qa.ToDoList.Entities.UserRepository;

@SpringBootTest
public class UserServiceTests {
	private static User testUser = new User(1L, "John", "Smith", "jsmith@gmail.com");
	private static UserDTO testUserDTO = new UserDTO();
	private static List<User> testUserList;
	private static List<UserDTO> testUserListDTO;
	
	@Autowired
	private UserService service;
	
	@MockBean
	private UserRepository repo;
	
	@BeforeAll
	public static void before() {
		testUserDTO.setID(1L);
		testUserDTO.setFullName("John Smith");
		testUserDTO.setEmail("jsmith@gmail.com");
		
		testUserList = new ArrayList<User>();
		testUserList.add(testUser);
		
		testUserListDTO = new ArrayList<UserDTO>();
		testUserListDTO.add(testUserDTO);
	}
	
	@Test
	void createTest() {
		Mockito.when(this.repo.save(testUser)).thenReturn(testUser);
		Assertions.assertThat(this.service.create(testUser)).isEqualTo(testUserDTO);
		Mockito.verify(this.repo, Mockito.times(1)).save(testUser);
	}
	
	@Test
	void createMultipleTest() {
		Mockito.when(this.repo.save(testUser)).thenReturn(testUser);
		Assertions.assertThat(this.service.createMultiple(testUserList)).isEqualTo(testUserListDTO);
		Mockito.verify(this.repo, Mockito.times(1)).save(testUser);
	}
	
	@Test
    public void readAllTest() {
		testUserList.add(testUser);
		testUserListDTO.add(testUserDTO);

		Mockito.when(this.repo.findAll()).thenReturn(testUserList);
		Assertions.assertThat(this.service.readAll()).isEqualTo(testUserListDTO);
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
    }
	
	@Test
    public void readByIdTest() {
		final Optional<User> testUser = Optional.ofNullable(new User(1L, "John", "Smith", "jsmith@gmail.com"));
		
		Mockito.when(this.repo.findById(1L)).thenReturn(testUser);
		Assertions.assertThat(this.service.readById(1L)).isEqualTo(testUserDTO);
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
    }
	
	@Test
	public void updateTest() {
		final Optional<User> testUser = Optional.ofNullable(new User(1L, "John", "Smith", "jsmith@gmail.com"));
		User updatedTestUser = new User(1L, "Jon", "Smith", "jsmith@yahoo.com");
		UserDTO updatedTestUserDTO = new UserDTO();
		updatedTestUserDTO.setID(1L);
		updatedTestUserDTO.setFullName("Jon Smith");
		updatedTestUserDTO.setEmail("jsmith@yahoo.com");
		
		Mockito.when(this.repo.findById(1L)).thenReturn(testUser);
		Assertions.assertThat(this.service.update(1L, updatedTestUser)).isEqualTo(updatedTestUserDTO);
		Mockito.verify(this.repo, Mockito.times(1)).findById(1L);
	}
	
	@Test
	public void deleteTest() {
		this.service.delete(1L);
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(1L);
	}
}
