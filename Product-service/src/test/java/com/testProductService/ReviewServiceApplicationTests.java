package com.testProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.product.app.controller.ReviewController;
import com.product.app.model.Category;
import com.product.app.model.Product;
import com.product.app.model.Review;

import com.product.app.service.ReviewServiceImpl;

@SpringBootTest(properties = "spring.main.lazy-initialization=true",
classes = {ReviewServiceImpl.class})
public class ReviewServiceApplicationTests {
	@InjectMocks
	ReviewController reviewController;
	@Mock
	ReviewServiceImpl reviewServiceImpl;
	
	Review review;

	private final String reviewId= "rev11";
	
	private final String reviewText="Good product";
	
	private final Integer rating=5;
	
	private final String userName="ross";
	
  
	List<Review> reviewList;
	
	private final String productId = "786";
	


	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
	
		review =new Review();
		
		review.setRating(rating);
		review.setReviewId(reviewId);
		review.setUserName(userName);
		review.setReviewText(reviewText);
		reviewList=new ArrayList<Review>();
		reviewList.add(review);
		
	}
	@Test
	void contextLoads() {
	}
	
	@Test
	void addReviewNotNull() {
		// first check that all the required parameters are in the product class
		assertNotNull(review, "review is null");
		
	}
	
   @Test
	void addReviewReviewIdNotNull() {
		assertNotNull(review.getReviewId(), "Review id is required");
	}
	
   @Test
  	void addReviewReviewTextNotNull() {
  		assertNotNull(review.getReviewText(), "Review text is required");
  	}
  	
	
   @Test
  	void addReviewRatingNotNull() {
  		assertNotNull(review.getRating(), "Rating is required");
  	}
   
   @Test
 	void addReviewProductNotNull() {
 		assertNotNull(review.getProduct(), "Product is required");
 	}

   @Test
  	void addReviewUsernameNotNull() {
  		assertNotNull(review.getUserName(), "Username is required");
  	}
   
   @Test
	void addReviewCheck() {
		when(reviewServiceImpl.addReview(review, productId)).thenReturn(review);
		Review reviewRest=reviewController.addReview(review, productId);
	   assertEquals(review, reviewRest);
		
	}
   
   
   @Test
 	void addReviewReviewIdCheck() {
	when(reviewServiceImpl.addReview(review,  productId)).thenReturn(review);
 	Review reviewRest=reviewController.addReview(review,  productId);
 		assertEquals(review.getReviewId(), reviewRest.getReviewId());
 		
 	}
   
   @Test
	void addReviewReviewTextCheck() {
		when(reviewServiceImpl.addReview(review,  productId)).thenReturn(review);
		Review reviewRest=reviewController.addReview(review,  productId);
		assertEquals(review.getReviewText(), reviewRest.getReviewText());
		
	}
   @Test
  	void addReviewRatingCheck() {
  		when(reviewServiceImpl.addReview(review,  productId)).thenReturn(review);
  	Review reviewRest=reviewController.addReview(review,  productId);
  		assertEquals(review.getRating(), reviewRest.getRating());
  		
  	}
   @Test
 	void addReviewUsernameCheck() {
 		when(reviewServiceImpl.addReview(review,  productId)).thenReturn(review);
 		Review reviewRest=reviewController.addReview(review,  productId);
		assertEquals(review.getUserName(), reviewRest.getUserName());
 		
 	}
   @Test
	void addReviewProductCheck() {
		when(reviewServiceImpl.addReview(review,  productId)).thenReturn(review);
		Review reviewRest=reviewController.addReview(review,  productId);
	  assertEquals(review.getProduct(), reviewRest.getProduct());
		
	}
   
   @Test
   void getAllReviews() {
	   when(reviewServiceImpl.getAllReviews()).thenReturn(reviewList);
	   assertEquals(1,reviewController.getAllReviews().size());
   }
   

}
