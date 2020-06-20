package com.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.model.Item;
@Repository
public interface ItemRepo extends ReactiveMongoRepository<Item,String> {

}
