package com.udacity.course3.reviews.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.course3.reviews.entities.Comment;
import com.udacity.course3.reviews.entities.Review;

/**
 * Data Jpa Repository for Comment Entity.
 *
 * @author Nishant
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
	/**
	 * Find all by review.
	 *
	 * @param review The review 
	 * @return the list of comments
	 */
	List<Comment> findAllByReview(Review review);

}
