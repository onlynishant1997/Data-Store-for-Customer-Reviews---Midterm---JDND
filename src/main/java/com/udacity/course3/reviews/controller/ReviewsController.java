package com.udacity.course3.reviews.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.entities.Review;
import com.udacity.course3.reviews.repositories.ProductRepository;
import com.udacity.course3.reviews.repositories.ReviewRepository;

/**
 * Controller for review entity.
 *
 * @author Nishant
 */
@RestController
public class ReviewsController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	/**
	 * Creates the review for product.
	 *
	 * @param productId the product id
	 * @param review    the review
	 * @return the Review or 404 if review not found.
	 */
	@RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
	public ResponseEntity<Review> createReviewForProduct(@PathVariable("productId") Integer productId,
			@Valid @RequestBody Review review) {
		Optional<Product> optional = productRepository.findById(productId);
		if (optional.isPresent()) {
			Product product = optional.get();
			review.setProduct(product);
			return ResponseEntity.ok(reviewRepository.save(review));
		}
		return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
	}

	/**
	 * List reviews for product.
	 *
	 * @param productId the product id
	 * @return the list of reviews.
	 */
	@RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
	public ResponseEntity<List<Review>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
		Product findProduct = new Product();
		findProduct.setId(productId);
		if ((reviewRepository.findAllByProduct(findProduct)).isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(reviewRepository.findAllByProduct(findProduct));
	}
}