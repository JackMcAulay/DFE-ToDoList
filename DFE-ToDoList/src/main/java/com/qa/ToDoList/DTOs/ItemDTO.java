package com.qa.ToDoList.DTOs;

import java.sql.Date;
import java.util.List;

import com.qa.ToDoList.Enums.Status;

import lombok.Data;

@Data
public class ItemDTO {
	
	private long ID;
	private String itemAuthor;
	private String itemTitle;
	private String itemContents;
	private Date dateCreated;
	private Date dateDue;
	private Status status;
	private List<String> tags;
	
	public ItemDTO() {
        super();
    }
	
	
}
