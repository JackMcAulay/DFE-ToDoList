package com.qa.ToDoList.Services;

import org.assertj.core.api.Assertions;
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

	@Autowired
	private UserService service;
	
	@MockBean
	private UserRepository repo;
	
	@Test
	void createTest() {
		final User testUser = new User(1L, "John", "Smith", "jsmith@gmail.com");
		final User testUserSaved = new User(1L, "John", "Smith", "jsmith@gmail.com");
		final UserDTO testUserDTO = new UserDTO();
		testUserDTO.setID(1L);
		testUserDTO.setFullName("John Smith");
		testUserDTO.setEmail("jsmith@gmail.com");
		
		Mockito.when(this.repo.save(testUser)).thenReturn(testUserSaved);
		Assertions.assertThat(this.service.create(testUser)).isEqualTo(testUserDTO);
		Mockito.verify(this.repo, Mockito.times(1)).save(testUser);
	}
}
