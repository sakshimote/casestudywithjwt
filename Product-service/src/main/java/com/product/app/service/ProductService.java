package com.product.app.service;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.product.app.model.Product;


public interface ProductService {
	
	public Product addProduct(Product product, String merchantId, String categoryName);
	
	public List<Product> getAllProducts();
	
	public Product getProductById(String productid);
	
	public List<Product> getProductByType(String producttype);
	
	public Product getProductByName( String productName);
	
	public List<Product> getProductByCategory( String category);
	
	public Product updateProducts(Product product, String productid);
	
	public String deleteProductById(String productid);

	public List<Product> getProductByMerchantId(String merchantId);
}
