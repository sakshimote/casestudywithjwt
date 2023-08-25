package com.product.app.service;

import java.security.Principal;
import java.util.List;

import com.product.app.model.Review;

public interface ReviewService {
	
	public Review addReview(Review review,String productName);
	public List<Review> getAllReviews();

}
