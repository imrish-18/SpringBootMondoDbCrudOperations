package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Item;
import com.repo.ItemRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ItemService {

	@Autowired
	private ItemRepo itemRepo;


	List<Item> itemList=new ArrayList<Item>();
	public Flux<Item> saveAll()
	{
		
		
	 itemList.add(new Item(null,"samsung tv","400")); 
	  itemList.add(new
		  Item(null,"lg tv","475"));  itemList.add(new
		  Item(null,"apple watch","599"));  itemList.add(new
		  Item(null,"headphones","399"));  return
		  itemRepo.deleteAll().thenMany(Flux.fromIterable(itemList)) //
		  .flatMap(itemRepo::save);
		 
		
	}
	public Mono<Void> deleteAll() {
	return itemRepo.deleteAll();
		
	}
	
	public Flux<Item> findAll(){
	return	itemRepo.findAll();
	}
	
}
