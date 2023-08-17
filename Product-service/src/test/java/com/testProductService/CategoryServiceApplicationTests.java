package com.testProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.product.app.controller.CategoryController;
import com.product.app.model.Category;

import com.product.app.service.CategoryServiceImpl;


@SpringBootTest(properties = "spring.main.lazy-initialization=true",
classes = {CategoryServiceImpl.class})
public class CategoryServiceApplicationTests {
	
	@InjectMocks
	CategoryController categoryController;
	
	@Mock
	CategoryServiceImpl categoryServiceImpl;
	
	Category category;
	List<Category> categoryList;

	private final String categoryId= "cat11";
	
	private final String categoryName="Electronics";
	


	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		category=new Category();
		
		category.setCategoryId(categoryId);
		category.setCategoryName(categoryName);
		
		categoryList=new ArrayList<Category>();
		categoryList.add(category);
		
	}
	
	@Test
	void contextLoads() {
	}
	
	
	@Test

	void addCategoryNotNull() {
		// first check that all the required parameters are in the product class
		assertNotNull(category, "category is null");
		
	}
	
	@Test
	
	void addCategoryCategoryNameNotNull() {
		assertNotNull(category.getCategoryName(), "Category name is required");
	}
	
	@Test
	
	void addCategoryCategoryIdNotNull() {
		assertNotNull(category.getCategoryId(), "Category id is required");
	}
	
	@Test
	void addCategoryCheck() {
		when(categoryServiceImpl.addCategory(category)).thenReturn(category);
		Category categoryRest=categoryController.addCategory(category);
		assertEquals(category, categoryRest);
		
	}
	@Test
	void addCategoryCategoryIdCheck() {
		when(categoryServiceImpl.addCategory(category)).thenReturn(category);
		Category categoryRest=categoryController.addCategory(category);
		assertEquals(category.getCategoryId(),categoryRest.getCategoryId());
		
	}
	
	@Test
	void addCategoryCategoryNameCheck() {
		when(categoryServiceImpl.addCategory(category)).thenReturn(category);
		Category categoryRest=categoryController.addCategory(category);
		assertEquals(category.getCategoryName(),categoryRest.getCategoryName());
		
	}
	
	
	@Test
	void getCategoryByName() {
    	when(categoryServiceImpl.getCategoryByName(categoryName)).thenReturn(category);
		
		category= categoryController.getCategoryByName(categoryName);
		
		assertNotNull(category);
		
		assertEquals(category.getCategoryName(),"Electronics");
	}
	
	@Test
	   void getAllCategories() {
		   when(categoryServiceImpl.getAllCategories()).thenReturn(categoryList);
		   assertEquals(1,categoryController.getAllCategories().size());
	   }
	   

}
