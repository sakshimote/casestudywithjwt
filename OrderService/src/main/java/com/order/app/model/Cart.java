package com.order.app.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
public class Cart {
	
	@Id
    private String cartId;
	
    private double totalPrice;
    
   
    private String userId;
    
   private String productName;
   private int quantity;
   private double productPrice;
   
   private List<ItemsAddedInCart> productsAdded;


	public void setProductsAdded(List<ItemsAddedInCart> productsAdded) {
	this.productsAdded = productsAdded;
}


	public Cart(String cartId, double totalPrice, String userId, String productName, int quantity, double productPrice) {
	super();
	this.cartId = cartId;
	this.totalPrice = totalPrice;
	this.userId = userId;
	this.productName = productName;
	this.quantity = quantity;
	this.productPrice = productPrice;
}


	public int getQuantity() {
	return quantity;
}



public List<ItemsAddedInCart> getProductsAdded() {
		return productsAdded;
	}


public void setQuantity(int quantity) {
	this.quantity = quantity;
}


	public String getProductName() {
	return productName;
}


public void setProductName(String productName) {
	this.productName = productName;
}



public double getProductPrice() {
	return productPrice;
}


public void setProductPrice(double productPrice) {
	this.productPrice = productPrice;
}


	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}




	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
    
    
    
}
