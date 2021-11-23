package com.qa.ToDoList.DTOs;

import lombok.Data;

@Data
public class UserDTO {
	
	private long ID;
	private String fullName;
	private String email;
	
	public UserDTO() {
        super();
    }
}
