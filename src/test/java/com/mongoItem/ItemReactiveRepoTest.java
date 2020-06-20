package com.mongoItem;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.model.Item;
import com.repo.ItemRepo;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
@TestInstance(Lifecycle.PER_CLASS)
@DataMongoTest
@AutoConfigureTestDatabase
public class ItemReactiveRepoTest {

 
	@Autowired
	ItemRepo itemRepo;
	
	List<Item> itemList=new ArrayList<Item>();
	
	@BeforeAll
	public void setUp() {
		itemList.add(new Item(null,"samsung tv","400"));
		itemList.add(new Item(null,"lg tv","475"));
		itemList.add(new Item(null,"apple watch","599"));
		itemList.add(new Item(null,"headphones","399"));
		itemRepo.deleteAll().thenMany(Flux.fromIterable(itemList))
		.flatMap(itemRepo::save)
		.doOnNext(res->{
			System.out.println("inserted item is..."+res);
		}).blockLast();
		
	}
	@Test
	public void getAllItems()
	{
		
		StepVerifier.create(itemRepo.findAll()).expectSubscription()
		.expectNextCount(4).verifyComplete();
	}
}
