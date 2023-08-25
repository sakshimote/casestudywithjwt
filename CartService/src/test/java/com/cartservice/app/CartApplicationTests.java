package com.cartservice.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.cartservice.app.controller.CartController;
import com.cartservice.app.model.Cart;
import com.cartservice.app.model.ItemsAddedInCart;
import com.cartservice.app.service.CartServiceImpl;


@SpringBootTest
class CartApplicationTests {
	@InjectMocks
	CartController cartController;
	
	@Mock
	CartServiceImpl cartServiceImpl;
	
	
	Cart cart;
	ItemsAddedInCart items;
	
	List<Cart> carts;
	
	private final String cartId="cart11";
	private final double totalPrice=500D;
	private final String userId="user1";
	private final String productName="iphone charger";
	private final int quantity=1;

	

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		cart=new Cart();
		cart.setCartId(cartId);
		cart.setQuantity(quantity);
		cart.setTotalPrice(totalPrice);
		cart.setUserId(userId);
		items=new ItemsAddedInCart();
		items.setProductName(productName);
		items.setQuantity(quantity);
		List<ItemsAddedInCart> itemslist=new ArrayList<ItemsAddedInCart>();
		itemslist.add(items);
		cart.setProductsAdded(itemslist);
		
		carts=new ArrayList<Cart>();
		carts.add(cart);
		
	}
	
	@Test
	void contextLoads() {
	}
	
	

	@Test
	void addCartNotNull() {
		// first check that all the required parameters are in the product class
		assertNotNull(cart, "cart is null");
		
	}
	
    @Test
	void addCartCartIdNotNull() {
		assertNotNull(cart.getCartId(), "Cart id is required");
	}
	
 
    @Test
   	void addCartQuantityNotNull() {
   		assertNotNull(cart.getQuantity(), "quantity is required");
   	}
    
    

 
    
    @Test
   	void addCartTotalPriceNotNull() {
   		assertNotNull(cart.getTotalPrice(), "Total price is required");
   	}
    @Test
   	void addCartProductAddedInCartNotNull() {
   		assertNotNull(cart.getProductsAdded(), "products added in cart is required");
   	}
    
    @Test
   	void addCartUserIdNotNull() {
   		assertNotNull(cart.getUserId(), "user id is required");
   	}
    
   @Test
    void addCartCheck() {
    	when(cartServiceImpl.AddCart(cart, userId)).thenReturn(cart);
    	Cart cartRest=cartController.AddCart(cart, userId);
    	assertEquals(cart, cartRest);
    }
   
   @Test
    void addCartcartIdCheck() {
   	when(cartServiceImpl.AddCart(cart, userId)).thenReturn(cart);
    	Cart cartRest=cartController.AddCart(cart, userId);
   	assertEquals(cart.getCartId(), cartRest.getCartId());
    }
   
    @Test
   void addCartProductQuantityCheck() {
  	when(cartServiceImpl.AddCart(cart, userId)).thenReturn(cart);
      Cart cartRest=cartController.AddCart(cart, userId);
    	assertEquals(cart.getQuantity(), cartRest.getQuantity());
   }
  @Test
       void addCartTotalPriceCheck() {
    	when(cartServiceImpl.AddCart(cart, userId)).thenReturn(cart);
  	    Cart cartRest=cartController.AddCart(cart, userId);
    	assertEquals(cart.getTotalPrice(), cartRest.getTotalPrice());
    }
    
    @Test
    void addCartUserIdCheck() {
    	when(cartServiceImpl.AddCart(cart, userId)).thenReturn(cart);
    	Cart cartRest=cartController.AddCart(cart, userId);
    	assertEquals(cart.getUserId(), cartRest.getUserId());
   }
    
    
   @Test
    void addCartProductsAddedCheck() {
    	when(cartServiceImpl.AddCart(cart, userId)).thenReturn(cart);
 	Cart cartRest=cartController.AddCart(cart, userId);
    	
   	assertEquals(cart.getProductsAdded(), cartRest.getProductsAdded());
  }
    
    
   @Test
	void getByCartId() {
		when(cartServiceImpl.getByCartId(cartId)).thenReturn(cart);
		Cart cartRest=cartController.getByCartId(cartId);
		assertNotNull(cartRest,"cart is not available in DB");
		assertEquals(cart.getCartId(), cartRest.getCartId());
		
	}
   @Test
   void AddQuantityOfProduct() {
	   when(cartServiceImpl.AddQuantityOfProduct(cartId, productName, quantity)).thenReturn(cart);
	   Cart cartRest=cartController.AddQuantityOfProduct(cartId, productName, quantity);
	   assertNotNull(cartRest,"cart is not available in DB");
	   assertNotNull(cartRest,"product name is invalid");
	  
		assertEquals(cart.getQuantity()+quantity, cartRest.getQuantity()+quantity);
   }
   
   @Test
   void RemoveQuantityOfProduct() {
	   when(cartServiceImpl.RemoveQuantityOfProduct(cartId, productName, quantity)).thenReturn(cart);
	   Cart cartRest=cartController.RemoveQuantityOfProduct(cartId, productName, quantity);
	   assertNotNull(cartRest,"cart is not available in DB");
	   assertNotNull(cartRest,"product name is invalid");
	  
		assertEquals(cart.getQuantity()-quantity, cartRest.getQuantity()-quantity);
   }
   @Test
   void AddMoreProductsToCart() {
	   when(cartServiceImpl.addMoreProductsInCart(cartId, productName, quantity)).thenReturn(cart);
	   Cart cartRest=cartController.addMoreProductsInCart(cartId, productName, quantity);
	   assertNotNull(cartRest,"product already present in cart");
	   assertNotNull(cartRest,"cart is not available in DB");
	   
		assertEquals(cart.getProductsAdded() ,cartRest.getProductsAdded());
   }
   @Test
	void  deleteCartBycartId() {
	   
		when(cartServiceImpl.deleteCart(cartId)).thenReturn("Successfully deleted");
		when(cartServiceImpl.getByCartId(cartId)).thenReturn(cart);
		String delete=cartController.deleteCart(cartId);
		assertEquals("Successfully deleted", delete ,"cart not avalable in db");
		
	}
   @Test
   void getAllCarts() {
	   when(cartServiceImpl.getAllCarts()).thenReturn(carts);
	   assertEquals(1,cartController.getAllCarts().size());
   }
}
