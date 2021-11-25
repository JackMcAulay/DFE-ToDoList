package com.qa.ToDoList.Entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.qa.ToDoList.Enums.Status;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;
	
	@Column
	private long userId;
	
	@Column
	private String itemTitle;
	
	@Column
	private String itemContents;
	
	@Column
	private Date dateCreated = new Date(System.currentTimeMillis());
	
	@Column
	private Date dateDue;
	
	@Column
	private Status status = Status.DUE;
	
	@ElementCollection
    @CollectionTable(name="listOfTags")
	private List<String> tags;

	public Item(long iD, String itemTitle, String itemContents, Date dueDate, List<String> tags) {
		super();
		ID = iD;
		this.itemTitle = itemTitle;
		this.itemContents = itemContents;
		this.dateDue = dueDate;
		this.tags = tags;
	}
}
