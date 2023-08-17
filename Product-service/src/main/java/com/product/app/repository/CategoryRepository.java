package com.product.app.repository;



import org.springframework.data.mongodb.repository.MongoRepository;

import com.product.app.model.Category;

public interface CategoryRepository extends MongoRepository<Category, String>{

	Category findByCategoryName(String categoryName);

	String deleteByCategoryName(String categoryName);

}
