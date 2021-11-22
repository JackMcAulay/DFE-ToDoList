package com.qa.ToDoList.Entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>{

	public Item findItemByID(long id);
	public List<Item> findAllByUserId(long id);
}

