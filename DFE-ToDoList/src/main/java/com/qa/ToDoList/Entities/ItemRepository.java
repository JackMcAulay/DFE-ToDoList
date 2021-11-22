package com.qa.ToDoList.Entities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>{

	public User findItemByID(long id);
}

