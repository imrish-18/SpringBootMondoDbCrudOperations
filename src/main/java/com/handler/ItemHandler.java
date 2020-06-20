package com.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.model.Item;
import com.service.ItemService;

import reactor.core.publisher.Mono;

@Component
public class ItemHandler {

	@Autowired
	private ItemService itemService;
	public  Mono<ServerResponse> getAllItems(ServerRequest serverRequest)
	{
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
		.body(itemService.findAll(),Item.class);
		
	}
	public  Mono<ServerResponse> postItems(ServerRequest serverRequest)
	{
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
		.body(itemService.saveAll(),Item.class);
		
	}
}
