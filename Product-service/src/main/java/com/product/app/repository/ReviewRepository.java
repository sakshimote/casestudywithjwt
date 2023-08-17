package com.product.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.product.app.model.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {

	List<Review> findByProductId(String productId);

}
