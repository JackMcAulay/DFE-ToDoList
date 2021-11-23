package com.qa.ToDoList.DTOs;

import lombok.Data;

@Data
public class UserDTO {
	
	private String name;
	private String email;
	
	public UserDTO() {
        super();
    }
}
