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

import com.udacity.course3.reviews.entities.Comment;
import com.udacity.course3.reviews.entities.Review;
import com.udacity.course3.reviews.repositories.CommentRepository;
import com.udacity.course3.reviews.repositories.ReviewRepository;

/**
 * Controller for Comment Entity.
 *
 * @author Nishant
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

	/** The comment repository. */
	@Autowired
	private CommentRepository commentRepository;

	/** The review repository. */
	@Autowired
	private ReviewRepository reviewRepository;

	
	/**
	 * Creates the comment for review.
	 *
	 * @param reviewId the review id
	 * @param comment the comment
	 * @return the comment or 404 if comment not found
	 */
	@RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
	public ResponseEntity<Comment> createCommentForReview(@PathVariable("reviewId") Integer reviewId,
			@Valid @RequestBody Comment comment) {
		Optional<Review> optional = reviewRepository.findById(reviewId);
		if (optional.isPresent()) {
			comment.setReview(optional.get());
			return ResponseEntity.ok(commentRepository.save(comment));
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	
	/**
	 * List comments for review.
	 *
	 * @param reviewId the review id
	 * @return the list of comments
	 */
	@RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
	public ResponseEntity<List<Comment>> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
		Review findReview = new Review();
		findReview.setId(reviewId);
		if ((commentRepository.findAllByReview(findReview)).isEmpty())    
            return ResponseEntity.notFound().build();
		return ResponseEntity.ok(commentRepository.findAllByReview(findReview));
	}
}