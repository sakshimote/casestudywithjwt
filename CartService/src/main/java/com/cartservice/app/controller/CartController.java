package com.cartservice.app.controller;

import java.util.List;

import javax.ws.rs.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cartservice.app.JwtUtil;
import com.cartservice.app.model.AuthenticationRequest;
import com.cartservice.app.model.AuthenticationResponse;
import com.cartservice.app.model.Cart;
import com.cartservice.app.service.CartServiceImpl;
import com.cartservice.app.service.MyUserDetails;


import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class CartController {
	
	@Autowired
	private CartServiceImpl cartServiceImpl;
	

	 @Autowired
		private AuthenticationManager authenticationManager;
		
		@Autowired
		private MyUserDetails userDetailsService;
		
		@Autowired
		private JwtUtil jwtTokeUtil;
		
		@PostMapping("/authenticate")
		@ApiOperation("autyenticate method accessed")
		public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)throws Exception{
			try { authenticationManager.authenticate(
					 new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			}
			
			catch (BadCredentialsException e) {
				throw new Exception("Incorrect username or password",e);
			}
			final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			System.out.println(userDetails);
			System.out.println("hii");
			final String jwt=jwtTokeUtil.generateToken(userDetails);
		
			
			
			return ResponseEntity.ok(new AuthenticationResponse(jwt));
		}


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
	
	
