package com.cartservice.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cartservice.app.model.Cart;
import com.cartservice.app.model.Product;

public interface CartService {
	
	public Cart AddCart(Cart cart,String userId);
	public Cart getByCartId(String cartId);
	public Cart getCartByUserId(String userId);
	public String deleteCart(String cartId);
	public Cart deleteItem(String userId,String productId);
	public List<Cart> getAllCarts();
	public Cart AddQuantityOfProduct(String userId, String productId, Integer quantity);
	public Cart RemoveQuantityOfProduct(String userId, String productId, Integer quantity);
	
	public Cart addMoreProductsInCart(String cartId, String productName, Integer quantity);
	public Cart removeAllItems(String userId);
}
