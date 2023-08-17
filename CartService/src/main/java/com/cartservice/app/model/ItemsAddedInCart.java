package com.cartservice.app.model;



import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ItemsAddedInCart {
	private String productId;
	 private String productName;
	   private int quantity;
	   private double productPrice;
	   private List<String> productImage;
	 


	
	public ItemsAddedInCart(String productId, String productName, int quantity, double productPrice,
			List<String> productImage) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.productPrice = productPrice;
		this.productImage = productImage;
	}


	public List<String> getProductImage() {
		return productImage;
	}


	public void setProductImage(List<String> productImage) {
		this.productImage = productImage;
	}


	public double getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}


	public ItemsAddedInCart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItemsAddedInCart(String productName, int quantity) {
		super();
		this.productName = productName;
		this.quantity = quantity;
	}
	
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	   
	   

}
