package com.qa.ToDoList.DTOs;

import java.sql.Date;

import lombok.Data;

@Data
public class ItemDTO {
	
	private String itemAuthor;
	private String itemTitle;
	private String itemContents;
	private Date dueDate;
	private Date dateCreated;
	
	public ItemDTO() {
        super();
    }
}
