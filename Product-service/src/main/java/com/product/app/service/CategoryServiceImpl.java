package com.product.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.product.app.model.Category;
import com.product.app.model.UserProfile;
import com.product.app.repository.CategoryRepository;


@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	//add category
	public Category addCategory(Category category) {
	    return categoryRepository.save(category);

	}
	//find by categoryName
	public Category getCategoryByName(String categoryName) {
		return categoryRepository.findByCategoryName(categoryName);
	}
	
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	public Category getCategoryById(String categoryId) {
		return categoryRepository.findById(categoryId).get();
	}
	
	public String deleteCategoryById(String categoryId) {
		 categoryRepository.deleteById(categoryId);
		 return "deleted category successfully";
	}

}
