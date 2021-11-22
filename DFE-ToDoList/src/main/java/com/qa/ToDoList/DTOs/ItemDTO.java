package com.qa.ToDoList.DTOs;

import java.sql.Date;

import lombok.Data;

@Data
public class ItemDTO {
	
	private String itemAuthor;
	private String itemTitle;
	private String itemContents;
	private Date dateCreated;
	private Date dateDue;
	
	public ItemDTO() {
        super();
    }
}
