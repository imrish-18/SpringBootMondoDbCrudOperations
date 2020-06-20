package com.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.handler.ItemHandler;

@Configuration
public class ItemRouter {

	@Bean
	public RouterFunction<ServerResponse> itemRouter(ItemHandler handler)
	{
		 return RouterFunctions.route(RequestPredicates.GET("/getAllItems"), handler::getAllItems);
	
	}
	@Bean
	public RouterFunction<ServerResponse> itemRouterPost(ItemHandler handler)
	{
		 return RouterFunctions.route(RequestPredicates.POST("/postItems"), handler::postItems);
	
	}
}
