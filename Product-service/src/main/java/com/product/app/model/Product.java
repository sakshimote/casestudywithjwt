package com.product.app.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Product {
	
	@Id
	private String productid;
	
	@Field
	private String merchantId;
	@Field
	private String merchantName;
	
	@Field
	private String productType;
	@Field
	private String productName;

	
	@Field
	private List<String> image;
	@Field
	private double price;
	@Field
	private String description;  
	@Field
	private Map<String, String> specification;  
	
	@Field
	private Category category;
	
	@Field
	private String categoryId;
	






	public Product(String productid, String merchantId, String merchantName, String productType, String productName,
			List<String> image, double price, String description, Map<String, String> specification, Category category,
			String categoryId) {
		super();
		this.productid = productid;
		this.merchantId = merchantId;
		this.merchantName = merchantName;
		this.productType = productType;
		this.productName = productName;
		this.image = image;
		this.price = price;
		this.description = description;
		this.specification = specification;
		this.category = category;
		this.categoryId = categoryId;
	}




	public String getCategoryId() {
		return categoryId;
	}




	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}




	public Category getCategory() {
		return category;
	}




	public void setCategory(Category category) {
		this.category = category;
	}







	public String getProductid() {
		return productid;
	}




	public void setProductid(String productid) {
		this.productid = productid;
	}




	public String getMerchantId() {
		return merchantId;
	}




	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}




	public String getMerchantName() {
		return merchantName;
	}




	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}




	public String getProductType() {
		return productType;
	}




	public void setProductType(String productType) {
		this.productType = productType;
	}




	public String getProductName() {
		return productName;
	}




	public void setProductName(String productName) {
		this.productName = productName;
	}





	public List<String> getImage() {
		return image;
	}




	public void setImage(List<String> image) {
		this.image = image;
	}




	public double getPrice() {
		return price;
	}




	public void setPrice(double price) {
		this.price = price;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public Map<String, String> getSpecification() {
		return specification;
	}




	public void setSpecification(Map<String, String> specification) {
		this.specification = specification;
	}






	
	

	public Product() {
		
	}
	
	
	


	







	
}
