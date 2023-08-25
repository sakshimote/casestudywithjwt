package com.product.app.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.app.model.Review;
import com.product.app.service.ReviewServiceImpl;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class ReviewController {
	
	@Autowired
	private ReviewServiceImpl reviewServiceImpl;
	
	@PostMapping("/addReview/{productId}")
	public Review addReview(@RequestBody Review review,
			@PathVariable("productId")String productId) {
		return reviewServiceImpl.addReview(review, productId);
	}
	
	@GetMapping("/reviews")
	public List<Review> getAllReviews(){
		return reviewServiceImpl.getAllReviews();
	}
	
	@GetMapping("/reviews/{productId}")
	public List<Review> getReviewsByProductId(@PathVariable("productId")String productId){
		return reviewServiceImpl.getReviewByProductId(productId);
	}
	
	@DeleteMapping("/delete/{reviewId}")
	public String deleteReview(@PathVariable("reviewId") String reviewId) {
		return reviewServiceImpl.deleteReviewByRid(reviewId);
	}

}
