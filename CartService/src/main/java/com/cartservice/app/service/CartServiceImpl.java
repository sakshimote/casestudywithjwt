package com.cartservice.app.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cartservice.app.model.Cart;
import com.cartservice.app.model.ItemsAddedInCart;
import com.cartservice.app.model.Product;
import com.cartservice.app.model.UserProfile;
import com.cartservice.app.repository.CartRepository;
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CartRepository cartRepository;



	@Override
	public Cart getByCartId(String cartId) {
		// TODO Auto-generated method stub
		return cartRepository.findById(cartId).get();
	}

	@Override
	public Cart AddQuantityOfProduct(String userId, String productId ,Integer quantity) {
		Cart cart=cartRepository.findByUserId(userId).get();

		cart.setCartId(cart.getCartId());
		cart.setUserId(userId);
	
		List<ItemsAddedInCart> list=cart.getProductsAdded();
	
		list.stream().map(i->{
			if(i.getProductId().equals(productId)) {
			i.setQuantity(i.getQuantity()+quantity);			
			}
			return i;	
			}).collect(Collectors.toList());
		
		cart.setProductsAdded(list);
		cart.setQuantity(quantity);
		cart.setTotalPrice(list.stream().map(c->c.getProductPrice()*c.getQuantity()).reduce(0.0, Double::sum));
		
		
		return cartRepository.save(cart);
	}
	
	
	@Override
	public Cart RemoveQuantityOfProduct(String userId, String productId, Integer quantity) {
		Cart cart=cartRepository.findByUserId(userId).get();

		
		cart.setCartId(cart.getCartId());
		cart.setUserId(userId);
		
		List<ItemsAddedInCart> list=cart.getProductsAdded();
		
		
		list.stream().map(i->{
			if(i.getProductId().equals(productId)) {
				if(i.getQuantity()==1) {
					
					throw new RuntimeException("cannot reduce quantity");
					
				}
			i.setQuantity(i.getQuantity()-quantity);
			
			}
			return i;	
			}).collect(Collectors.toList());
		
	 	
		cart.setProductsAdded(list);
		cart.setQuantity(quantity);
		cart.setTotalItems(list.size());
		cart.setTotalPrice(list.stream().map(c->c.getProductPrice()*c.getQuantity()).reduce(0.0, Double::sum));
		
		return cartRepository.save(cart);
	}
	



	@Override
	public String deleteCart(String cartId) {
		Cart cart=cartRepository.findById(cartId).get();
		// TODO Auto-generated method stub
		 cartRepository.delete(cart);
		 return "Cart Deleted Successfully";
	}
	

	@Override
	public Cart addMoreProductsInCart(String userId, String productId, Integer quantity) {
		
		Cart cart=cartRepository.findByUserId(userId).get();

		Product product=restTemplate.getForObject(
				"http://localhost:8096/product/allproduct/"+productId, Product.class);
		cart.setCartId(cart.getCartId());
		cart.setUserId(userId);
		
				
		List<ItemsAddedInCart> list=cart.getProductsAdded();
	      
	if(	list.stream().anyMatch(o -> productId.equals(o.getProductId()))) {
		throw new RuntimeException("Product already present, add quantity of product");
	}else {

	        ItemsAddedInCart newItems=new ItemsAddedInCart();
	        newItems.setProductId(productId);
			newItems.setProductName(product.getProductName());
			newItems.setQuantity(quantity);
			newItems.setProductPrice(product.getPrice());
			newItems.setProductImage(product.getImage());
			list.add(newItems);
	      	cart.setProductId(productId);
			
			cart.setProductsAdded(list);
		cart.setTotalPrice(list.stream().map(c->c.getProductPrice()*c.getQuantity()).reduce(0.0, Double::sum));
			cart.setQuantity(quantity);

			cart.setTotalItems(list.size());

		
		return cartRepository.save(cart);
	}
		}

	@Override
	public Cart getCartByUserId(String userId) {
		// TODO Auto-generated method stub
		return cartRepository.findByUserId(userId).get();
	}

	@Override
	public Cart AddCart(Cart cart, String userId) {
	
				   cart.setUserId(userId);
					List<ItemsAddedInCart> productsAddedList=new ArrayList<>();
					ItemsAddedInCart inCart=new ItemsAddedInCart();
					productsAddedList.add(inCart);
					cart.setProductsAdded(productsAddedList);
					productsAddedList.remove(0);
					return cartRepository.save(cart);
			}

	@Override
	public List<Cart> getAllCarts() {
		// TODO Auto-generated method stub
		return cartRepository.findAll();
	}

	@Override
	public Cart deleteItem(String userId, String productId) {
		Cart cart=cartRepository.findByUserId(userId).get();
				cart.setCartId(cart.getCartId());
				
		cart.setUserId(userId);
	
		List<ItemsAddedInCart> list=cart.getProductsAdded();
		
		if(list.isEmpty())
			throw new RuntimeException("item is not there");
	
		list.remove(list.stream().filter(i->i.getProductId().equals(productId)).findFirst().get());
	
		cart.setProductsAdded(list);
		cart.setTotalItems(list.size());
		cart.setTotalPrice(list.stream().map(c->c.getProductPrice()*c.getQuantity()).reduce(0.0, Double::sum));
			
		return cartRepository.save(cart);
	}
	
	@Override
  	public Cart removeAllItems(String userId) {
		Cart cart=cartRepository.findByUserId(userId).get();
				cart.setCartId(cart.getCartId());
				
		cart.setUserId(userId);
	
		List<ItemsAddedInCart> list=cart.getProductsAdded();
		list.removeAll(list);
		cart.setProductsAdded(list);
		cart.setTotalItems(list.size());
		cart.setTotalPrice(0);
			
		return cartRepository.save(cart);
	}
	
	
	}


	
	

