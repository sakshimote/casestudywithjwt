package com.product.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Category {
	
	@Id
	private String categoryId;
	
	@Field
	private String categoryName;
	
	@Field
	private String imgUrl;
	
	

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	public Category(String categoryId, String categoryName, String imgUrl) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.imgUrl = imgUrl;
	}

	public Category() {
		
	}
	
	

	
	
	
	
	
	

}
