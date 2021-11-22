package com.qa.ToDoList.Entities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long>{

	public User findUserByID(long id);
}
