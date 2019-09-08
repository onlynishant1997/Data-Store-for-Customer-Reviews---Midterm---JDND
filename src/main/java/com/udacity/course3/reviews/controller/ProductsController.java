package com.udacity.course3.reviews.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.repositories.ProductRepository;

/**
 * Controller for product entity.
 *
 * @author Nishant
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductRepository productRepository;

	/**
	 * Creates the product.
	 *
	 * @param product the product.
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@Valid @RequestBody Product product) {
		productRepository.save(product);
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the product or 404 if product not found.s
	 */
	@RequestMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable("id") Integer id) {
		return ResponseEntity.of(productRepository.findById(id));
	}

	/**
	 * List products.
	 *
	 * @return the list of products.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Product> listProducts() {
		return productRepository.findAll();
	}

}