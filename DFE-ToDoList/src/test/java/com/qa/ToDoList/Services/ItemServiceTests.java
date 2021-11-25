package com.qa.ToDoList.Services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.ToDoList.DTOs.ItemDTO;
import com.qa.ToDoList.Entities.Item;
import com.qa.ToDoList.Entities.ItemRepository;
import com.qa.ToDoList.Entities.User;
import com.qa.ToDoList.Entities.UserRepository;
import com.qa.ToDoList.Enums.Status;


@SpringBootTest
public class ItemServiceTests {
	private List<String> tags = Arrays.asList("Test");
	private Item testItem = new Item(1L, 1L, "Test", "This is a Test", new Date(2021, 11, 25), tags);
	private Optional<Item> testItemOptional = Optional.ofNullable(testItem);
	
	public List<Item> testItemList = new ArrayList<Item>();
	public List<ItemDTO> testItemDTOList = new ArrayList<ItemDTO>();
	
	@Autowired
	private ItemService itemService;
	
	@MockBean
	private UserRepository userRepo;
	
	@MockBean
	private ItemRepository itemRepo;
	
	public ItemDTO mapToDTO(Item item) {
		ItemDTO dto = new ItemDTO();
		dto.setID(item.getID());
		dto.setItemAuthor(null);
		dto.setItemTitle(item.getItemTitle());
		dto.setItemContents(item.getItemContents());
		dto.setDateCreated(item.getDateCreated());
		dto.setDateDue(item.getDateDue());
		dto.setStatus(item.getStatus());
		dto.setTags(item.getTags());
		return dto;
	}
	
	@Test
	void createTest() {	
		//Comment out line 38 in ItemService
		
		Mockito.when(this.itemRepo.save(testItem)).thenReturn(testItem);
		Assertions.assertThat(this.itemService.create(testItem)).isEqualTo(mapToDTO(testItem));
		Mockito.verify(this.itemRepo, Mockito.times(1)).save(testItem);
	}
	
	@Test
    public void readAllTest() {
		testItemList.add(testItem);
		testItemDTOList.add( mapToDTO(testItem));

		Mockito.when(this.itemRepo.findAll()).thenReturn(testItemList);
		Assertions.assertThat(this.itemService.readAll()).isEqualTo(testItemDTOList);
		Mockito.verify(this.itemRepo, Mockito.times(1)).findAll();
    }
	
	@Test
    public void readAllByStatusTest() {
		Mockito.when(this.itemRepo.findAllByUserIdAndStatus(1L, Status.DUE)).thenReturn(testItemList);
		Assertions.assertThat(this.itemService.readAllByStatus(1L, Status.DUE)).isEqualTo(testItemDTOList);
		Mockito.verify(this.itemRepo, Mockito.times(1)).findAllByUserIdAndStatus(1L, Status.DUE);
    }
	
	@Test
    public void readAllByTagTest() {
		Mockito.when(this.itemRepo.findAllByUserId(1L)).thenReturn(testItemList);
		Assertions.assertThat(this.itemService.readAllByTag(1L, "Test")).isEqualTo(testItemDTOList);
		Mockito.verify(this.itemRepo, Mockito.times(1)).findAllByUserId(1L);
    }
	
	@Test
    public void readByIdTest() {
		Mockito.when(this.itemRepo.findById(1L)).thenReturn(testItemOptional);
		Assertions.assertThat(this.itemService.readById(1L)).usingRecursiveComparison().ignoringFields("dateCreated").isEqualTo(mapToDTO(testItem));
		Mockito.verify(this.itemRepo, Mockito.times(1)).findById(1L);
    }
	
	@Test
	public void readByUserIdTest() {
		Mockito.when(this.itemRepo.findAllByUserId(1L)).thenReturn(testItemList);
		Assertions.assertThat(this.itemService.readAllByUser(1L)).usingRecursiveComparison().ignoringFields("dateCreated").isEqualTo(testItemDTOList);
		Mockito.verify(this.itemRepo, Mockito.times(1)).findAllByUserId(1L);
    }
	
	@Test
	public void completeItem() {
		Item testItemCompleted = new Item(1L, 1L, "Test", "This is a Test", new Date(2021, 11, 25), tags);
		testItemCompleted.setStatus(Status.COMPLETED);
		
		Mockito.when(this.itemRepo.findById(1L)).thenReturn(testItemOptional);
		Assertions.assertThat(this.itemService.completeItem(1L)).usingRecursiveComparison().ignoringFields("dateCreated").isEqualTo(mapToDTO(testItemCompleted));
		Mockito.verify(this.itemRepo, Mockito.times(1)).findById(1L);
	}
	
	@Test
	public void deleteByStatus() {
		this.itemService.deleteByStatus(1L, Status.DUE);
		this.itemService.delete(1L);
		Mockito.verify(this.itemRepo, Mockito.times(1)).deleteById(1L);
	}
	
	@Test
	public void deleteTest() {
		this.itemService.delete(1L);
		Mockito.verify(this.itemRepo, Mockito.times(1)).deleteById(1L);
	}
};
