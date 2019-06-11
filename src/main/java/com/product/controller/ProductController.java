package com.product.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.product.model.Product;
import com.product.model.SoldItem;
import com.product.repository.ProductRepository;
import com.product.repository.SoldItemRepository;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductRepository repository;

	@Autowired
	SoldItemRepository soldItemRepo;

	@Autowired
	SoldItem soldItem;

	@Autowired
	MongoTemplate template;

	@RequestMapping(method = RequestMethod.GET, path = "/listProducts")
	public Iterable<Product> product() {
		logger.info("Fetch list of products");
		return repository.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/listSoldProducts")
	public Iterable<SoldItem> soldProduct() {
		logger.info("Fetch list of products");

		return soldItemRepo.findAll();
	}

	@RequestMapping(method = RequestMethod.POST, path = "/addProduct")
	public Product save(@RequestBody Product product) {
		repository.save(product);

		return product;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getProduct/{id}")
	public Optional<Product> getProduct(@PathVariable String id) {
		return repository.findById(id);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/soldProduct/{id}/{NumberOfItem}")
	public Product update(@PathVariable String id, @PathVariable int NumberOfItem) {
		Optional<Product> optProduct = repository.findById(id);
		Product b = optProduct.get();

		if (b.getStockCount() != 0) {
			b.setStockCount(b.getStockCount() - NumberOfItem);
			repository.save(b);
			boolean flag = false;

			Optional<SoldItem> soldProduct = soldItemRepo.findById(id);

			if (soldProduct.isPresent()) {
				SoldItem s = soldProduct.get();
				if (s != null) {
					s.setSoldCount(s.getSoldCount() + NumberOfItem);
					soldItemRepo.save(s);
					flag = true;
				}
			}

			if (!flag) {
				soldItem.setId(b.getId());
				soldItem.setSoldCount(NumberOfItem);
				soldItem.setName(b.getName());

				soldItemRepo.save(soldItem);
			}
			return b;
		}
		return null;
	}

}
