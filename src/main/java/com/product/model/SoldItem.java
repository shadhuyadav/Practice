package com.product.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection = "lenskart-sold-data")
public class SoldItem {

	String id;
	String name;

	int SoldCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSoldCount() {
		return SoldCount;
	}

	public void setSoldCount(int soldCount) {
		SoldCount = soldCount;
	}

}