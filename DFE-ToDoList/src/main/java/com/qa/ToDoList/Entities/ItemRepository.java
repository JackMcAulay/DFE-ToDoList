package com.qa.ToDoList.Entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.ToDoList.Enums.Status;

public interface ItemRepository extends JpaRepository<Item, Long>{

	public Item findItemByID(long id);
	public List<Item> findAllByUserId(long id);
	public List<Item> findAllByUserIdAndStatus(long userId, Status status);
}

