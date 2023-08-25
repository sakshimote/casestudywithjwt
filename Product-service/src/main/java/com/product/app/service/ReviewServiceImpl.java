package com.product.app.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.product.app.model.Product;
import com.product.app.model.Review;
import com.product.app.model.UserProfile;
import com.product.app.repository.ProductRepository;
import com.product.app.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Review addReview(Review review, String productId) {
	
	
			Product product=productRepository.findById(productId).get();
			review.setProduct(product);
			review.setProductId(product.getProductid());
			
			return reviewRepository.save(review) ;
	}
	
	public List<Review> getAllReviews(){
		return reviewRepository.findAll();
	}
	
	public List<Review> getReviewByProductId(String productId) {
		return reviewRepository.findByProductId(productId);
	}
	
	public String deleteReviewByRid(String reviewId) {
	reviewRepository.deleteById(reviewId);
	return "review deleted successfully!!";
	}

}
