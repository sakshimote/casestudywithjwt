package com.testProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;


import com.product.app.controller.ProductController;
import com.product.app.model.Category;
import com.product.app.model.Product;
import com.product.app.service.ProductServiceImpl;


@SpringBootTest(properties = "spring.main.lazy-initialization=true",
classes = {ProductServiceImpl.class})
public class ProductServiceApplicationTests {

	@InjectMocks
	ProductController productController;
	
	@Mock
	ProductServiceImpl produServiceImpl;
	
	Product product;
	
	Category category;
	
	List<Product> productlist;
	
	private final String productId = "786";
	
	private final String merchantId="user1";
	private final String merchantName="user";
	
	private final String producttype= "premium jewellery";
	
	private final String productName="Tanishq Gold";
	

	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		product = new Product();
		
		category = new Category("category1","Women Fashion","hsjh");
		
		List<String> image = new ArrayList<>();
		image.add("url1");
		
		product.setProductid(productId);
		product.setProductType(producttype);
		product.setProductName(productName);
		product.setCategory(category);
		product.setDescription("Made by 24 carat gold");
		product.setMerchantId(merchantId);
		product.setMerchantName(merchantName);
		Map<String,String> specifications=new HashMap<>();
		specifications.put("Caratage", "24 carat is pure gold with no other metals");
		product.setSpecification(specifications);
		product.setPrice(50000);
		product.setImage(image);
		productlist = new ArrayList<Product>();
		productlist.add(product);
		
		
	}
	@Test
	void contextLoads() {
	}
	
	
	@Test
	
	void addProduct() {
		// first check that all the required parameters are in the product class
		assertNotNull(product, "product is null");
		
	}
	
	@Test

	void addProductNotNull() {
		// first check that all the required parameters are in the product class
		
		assertNotNull(product, "product is null");
	}
	
	@Test

	void addProductProductTypeNotNull() {
		assertNotNull(product.getProductType(), "Product type is required");
	}
	
	@Test

	void addProductProductSpecificationNotNull() {
		assertNotNull(product.getSpecification(), "Specifications of product is required");
	}
	
	@Test

	void addProductProductMerchantNameNotNull() {
		assertNotNull(product.getMerchantName(), "Merchant name is required");
	}
	
	
	@Test
	
	void addProductProductNameNotNull() {
		assertNotNull(product.getProductName(), "Product Name is required");
	}
	
	
	@Test
	
	void addProductCategoryNotNull() {
		assertNotNull(product.getCategory(), "product category is required");
	}
	
	
	@Test
	
	void addProductDescriptionNotNull() {
		assertNotNull(product.getDescription(), "product description is required");
	}
	
	@Test
	
	void addProductPriceNotNull() {
		assertNotNull(product.getPrice(), "product price is required");
	}
	
	@Test
	
	void addProductImageNotNull() {
		assertNotNull(product.getImage(), "Product image is required");
	}
	
	@Test
	
	void getAllProducts() {
		
		when(produServiceImpl.getAllProducts()).thenReturn(productlist);
		assertEquals(1,produServiceImpl.getAllProducts().size() );
	}
	
	@Test
	
	void addProductCheck() {
		when(produServiceImpl.addProduct(product, merchantId,category.getCategoryName())).thenReturn(product);
		
		Product productRest = produServiceImpl.addProduct(product, merchantId,category.getCategoryName());
		
		assertNotNull(productRest);
	}
	
	@Test
	
	void addProductProductNameCheck() {
		when(produServiceImpl.addProduct(product, merchantId,category.getCategoryName())).thenReturn(product);
		
		Product productRest = productController.addProduct(product, merchantId,category.getCategoryName());
		
		assertEquals(product.getProductName(), productRest.getProductName());
	}
	
	@Test
	
	void addProductProductCategoryCheck() {
		when(produServiceImpl.addProduct(product, merchantId,category.getCategoryName())).thenReturn(product);
		
		Product productRest = productController.addProduct(product, merchantId,category.getCategoryName());
		
		assertEquals(product.getCategory().getCategoryName(), productRest.getCategory().getCategoryName());
	}
	
	@Test
	
	void addProductProductTypeCheck() {
		when(produServiceImpl.addProduct(product, merchantId,category.getCategoryName())).thenReturn(product);
		
		Product productRest = productController.addProduct(product, merchantId,category.getCategoryName());
		
		assertEquals(product.getProductType(), productRest.getProductType());
	}
	
	
	@Test
	
	void addProductProductDescriptionCheck() {
		when(produServiceImpl.addProduct(product, merchantId,category.getCategoryName())).thenReturn(product);
		
		Product productRest = productController.addProduct(product, merchantId,category.getCategoryName());
		
		assertEquals(product.getDescription(), productRest.getDescription());
	}
	
	
	
	@Test
	
	void addProductProductPriceCheck() {
		when(produServiceImpl.addProduct(product, merchantId,category.getCategoryName())).thenReturn(product);
		
		Product productRest = productController.addProduct(product, merchantId,category.getCategoryName());
		
		assertEquals(product.getPrice(), productRest.getPrice());
	}
	
	@Test
	
	void addProductProductImageCheck() {
		when(produServiceImpl.addProduct(product, merchantId,category.getCategoryName())).thenReturn(product);
		
		Product productRest = productController.addProduct(product, merchantId,category.getCategoryName());
		
		assertEquals(product.getImage().size(), productRest.getImage().size());
	}
	
	@Test

	void getProductById() {
		when(produServiceImpl.getProductById(productId)).thenReturn(product);
		
		Product productRest=productController.getProductById(productId);
		
		assertNotNull(productRest, "Product not avalable in db");
		
		assertEquals(product.getProductid(), productRest.getProductid());
		
		
		
	}
	
	@Test
	
	void getProductByType() {
		when(produServiceImpl.getProductByType(producttype)).thenReturn(productlist);
		
		productlist = productController.getProductByType(producttype);
		
		assertNotNull(product);
		
		assertEquals(product.getProductType(), "premium jewellery");
		
		
	}
	
	@Test
	
	void getProductByName() {
		when(produServiceImpl.getProductByName(productName)).thenReturn(product);
		
		product= productController.getProductByName(productName);
		
		assertNotNull(product);
		
		assertEquals(product.getProductName(),"Tanishq Gold");
	}
	
	
	@Test
	
	void getProductByCategory() {
		
		when(produServiceImpl.getProductByCategory(category.getCategoryName())).thenReturn(productlist);
		
		productlist= productController.getProductByCategoryId(category.getCategoryName());
		
		assertNotNull(product);
		
		assertEquals(product.getCategory().getCategoryName(),"Women Fashion");
	}
	

	@Test
	
	void deleteProductById() {
		
		when(produServiceImpl.deleteProductById(productId)).thenReturn("Deleted Succesfully ");
		
		when(produServiceImpl.getProductById(productId)).thenReturn(product);
		
		String delete = productController.deleteProductById(productId);
		
		assertEquals("Deleted Succesfully ", delete ,"Product not avalable in db");
		
	}
	
}
