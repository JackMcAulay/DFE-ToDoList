package com.qa.ToDoList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DfeToDoListApplication {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ApplicationContext context = SpringApplication.run(DfeToDoListApplication.class, args);
	}

}
