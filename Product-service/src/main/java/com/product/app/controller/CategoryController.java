package com.product.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.app.model.Category;
import com.product.app.service.CategoryServiceImpl;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	@PostMapping("/addCategory")
	public Category addCategory(@RequestBody Category category) {
		return categoryServiceImpl.addCategory(category);
		
	}
	
	//find by categoryName
	@GetMapping("/category/{categoryName}")
		public Category getCategoryByName(@PathVariable String categoryName) {
			return categoryServiceImpl.getCategoryByName(categoryName);
		}
	
	@GetMapping("/categories")
	public List<Category> getAllCategories(){
		return categoryServiceImpl.getAllCategories();
	}
	
	@GetMapping("/category/byId/{categoryId}")
	public Category getCategoryById(String categoryId) {
		return categoryServiceImpl.getCategoryById(categoryId);
	}
	
	@DeleteMapping("/delete/{categoryId}")
	public String deleteCategory(@PathVariable("categoryId") String categoryId) {
		return categoryServiceImpl.deleteCategoryById(categoryId);
	}
		

}
