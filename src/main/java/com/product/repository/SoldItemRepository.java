package com.product.repository;


import org.springframework.data.repository.CrudRepository;

import com.product.model.SoldItem;


public interface SoldItemRepository extends CrudRepository<SoldItem, String> {
	
	public Iterable<SoldItem> findByName(String name);
   
}
