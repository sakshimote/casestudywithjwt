package com.product.app.controller;



import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.app.JwtUtil;
import com.product.app.model.AuthenticationRequest;
import com.product.app.model.AuthenticationResponse;
import com.product.app.model.Category;
import com.product.app.model.Product;
import com.product.app.service.MyUserDetails;
import com.product.app.service.ProductService;
import com.product.app.service.ProductServiceImpl;


import ch.qos.logback.classic.Logger;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class ProductController {
	 @Autowired
	private ProductServiceImpl productServiceImpl;
	
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
			
	
	
	org.slf4j.Logger logger =LoggerFactory.getLogger(ProductController.class);

	
	//Post all 
	
	@PostMapping("/allproduct/{merchantId}/{categoryId}")
	@ApiOperation(value = "Inserts product details",
	notes = "registers on shopping cart website",
	response = Product.class)
	public Product addProduct(@RequestBody Product product ,@PathVariable ("merchantId") String merchantId,
			@PathVariable("categoryId")String categoryId) {
	   return  productServiceImpl.addProduct(product,merchantId,categoryId);
	}
    
	//get all product
	
	@GetMapping("/allproduct")
	public List<Product> getAllProducts() {
		
		List<Product> productList = productServiceImpl.getAllProducts();
		logger.trace("get product meathod get acceseed");
	
		return productList;
	}
	
	
	//get Product By product Id
	
	@GetMapping("/allproduct/{productId}")
	public Product getProductById(@PathVariable("productId")String productId) {
		
		Product product = productServiceImpl.getProductById(productId);
		
		return product;
	}

	
	
	//get Product By Product type
	
	
	@GetMapping("/allproduct/type/{producttype}")
	public List<Product> getProductByType(@PathVariable ("producttype") String producttype) {
		// TODO Auto-generated method stub
		return productServiceImpl.getProductByType(producttype);
	}
 
    //get product By Name
	
	@GetMapping("/allproduct/name/{productname}")
	public Product getProductByName(@PathVariable("productname") String productname) {
		
		return productServiceImpl.getProductByName(productname);
	}

	//get Product By Category
	
	@GetMapping("/allproduct/category/{categoryId}")
	public List<Product> getProductByCategoryId(@PathVariable("categoryId") String categoryId) {
	
		return productServiceImpl.getProductByCategoryId(categoryId);
				}

	
	//Update Profile by Id
	
	@PutMapping("/allproduct/{productId}")
	public Product updateProducts(@RequestBody Product product,
			                           @PathVariable("productId") String productId ) {
		
		// TODO Auto-generated method stub
		return productServiceImpl.updateProducts(product, productId) ;
	}

	//Delete Profile By id
	
	@DeleteMapping("/allproduct/{productId}")
	public String deleteProductById(@PathVariable("productId") String productId) {
		// TODO Auto-generated method stub
		return productServiceImpl.deleteProductById(productId);
	}


	@GetMapping("/allproduct/bymerchantid/{merchantId}")
	public List<Product> getProductsByMerchantId(@PathVariable("merchantId") String merchantId){
		return productServiceImpl.getProductByMerchantId(merchantId);
	}

	

}
