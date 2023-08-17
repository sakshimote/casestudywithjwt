package com.cartservice.app.controller;

import java.util.List;

import javax.ws.rs.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartservice.app.model.Cart;
import com.cartservice.app.service.CartServiceImpl;


@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class CartController {
	
	@Autowired
	private CartServiceImpl cartServiceImpl;
	
	@PostMapping("/addcart/{userId}")
	public Cart AddCart(@RequestBody Cart cart, @PathVariable("userId") String userId) {
		return cartServiceImpl.AddCart(cart, userId);
	}
	
	@GetMapping("/getCart/{cartId}")
	public Cart getByCartId(@PathVariable("cartId") String cartId) {
		return cartServiceImpl.getByCartId(cartId);
	}
	
	@PostMapping("/add/quantity/{userId}/{productId}/{quantity}")
	public Cart AddQuantityOfProduct(@PathVariable("userId")String userId,
		@PathVariable("productId")	String productId,@PathVariable("quantity") Integer quantity) {
		return cartServiceImpl.AddQuantityOfProduct(userId, productId, quantity);
	}
	
	@GetMapping("/byUser/{userId}")
	public Cart getCartByUserId( @PathVariable("userId") String userId) {
		return cartServiceImpl.getCartByUserId(userId);
	}
	
	@DeleteMapping("/delete/{cartId}")
	public String deleteCart(@PathVariable("cartId") String cartId) {
		return cartServiceImpl.deleteCart(cartId);
	}
	
	@GetMapping("/carts")
	public List<Cart> getAllCarts(){
		return cartServiceImpl.getAllCarts();
	}
	
	@PostMapping("/remove/quantity/{userId}/{productId}/{quantity}")
	public Cart RemoveQuantityOfProduct(@PathVariable("userId") String userId, 
			@PathVariable("productId")	String productId, 
			@PathVariable("quantity")Integer quantity) {
		return cartServiceImpl.RemoveQuantityOfProduct(userId, productId, quantity);
	}
	@PostMapping("/add/items/{userId}/{productId}/{quantity}")
	public Cart addMoreProductsInCart(@PathVariable("userId") String userId,
			@PathVariable("productId")	String productId,
			@PathVariable("quantity")Integer quantity) {
		return cartServiceImpl.addMoreProductsInCart(userId, productId, quantity);
	}
	@PutMapping("/remove/item/{userId}/{productId}")
	public Cart deleteItem(@PathVariable("userId")  String userId, @PathVariable("productId")
	String productId) {
		return cartServiceImpl.deleteItem(userId, productId);
	}
	@PutMapping("/removeAll/items/{userId}")
	public Cart removeAllItems(@PathVariable("userId")  String userId) {
		return cartServiceImpl.removeAllItems(userId);
	}
	}
	
	
