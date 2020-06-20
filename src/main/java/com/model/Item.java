package com.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {


	@Id
	private String id;
	private String description;
	private String price;
	public Item(String id, String description, String price) {
		super();
		this.id = id;
		this.description = description;
		this.price = price;
	}
	
}
