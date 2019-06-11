package com.product.repository;


import org.springframework.data.repository.CrudRepository;

import com.product.model.Product;


public interface ProductRepository extends CrudRepository<Product, String> {
	
	public Iterable<Product> findByName(String name);
   
}
