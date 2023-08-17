package com.cartservice.app.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Document
public class Cart {
	@Id
    private String cartId;
	
    private double totalPrice;
@Indexed(unique = true)
    private String userId;

private int totalItems;

      private int quantity;

   @Indexed(unique = true)
   private String productId;
   
   private List<ItemsAddedInCart> productsAdded;
    

	public Cart(String cartId, double totalPrice, String userId, int totalItems, int quantity, String productId,
		List<ItemsAddedInCart> productsAdded) {
	super();
	this.cartId = cartId;
	this.totalPrice = totalPrice;
	this.userId = userId;
	this.totalItems = totalItems;
	this.quantity = quantity;
	this.productId = productId;
	this.productsAdded = productsAdded;
}

	public int getTotalItems() {
		return totalItems;
	}



	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}



	public void setProductsAdded(List<ItemsAddedInCart> productsAdded) {
	this.productsAdded = productsAdded;
}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
