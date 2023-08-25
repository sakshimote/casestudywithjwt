package com.product.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Review {
	
	@Id
	private String reviewId;
	
	@Field
	private String reviewText;
	
	private Integer rating;
	
	private Product product;
	
	private String productId;
	
	
	
	private String userName;
	
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Review(String reviewId, String reviewText, Integer rating, Product product, String productId,
			String userName) {
		super();
		this.reviewId = reviewId;
		this.reviewText = reviewText;
		this.rating = rating;
		this.product = product;
		this.productId = productId;
		this.userName = userName;
	}



	public String getProductId() {
		return productId;
	}



	public void setProductId(String productId) {
		this.productId = productId;
	}



	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}



	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}




	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}







}
