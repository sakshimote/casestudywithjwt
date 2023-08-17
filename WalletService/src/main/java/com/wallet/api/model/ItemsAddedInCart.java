package com.wallet.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ItemsAddedInCart {
	 private String productName;
	   private int quantity;
	   
	   
	public ItemsAddedInCart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItemsAddedInCart(String productName, int quantity) {
		super();
		this.productName = productName;
		this.quantity = quantity;
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
