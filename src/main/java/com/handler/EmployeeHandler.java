package com.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.model.Employee;
import com.service.EmployeeService;

import reactor.core.publisher.Mono;

/**
 * The Class EmployeeHandler.
 */
@Component
public class EmployeeHandler {



/** The emp. */
@Autowired
  private EmployeeService emp;
 
  
  /**
   * Gets the data.
   *
   * @param serverRequest the server request
   * @return the data
   */
  public Mono<ServerResponse> getAllEmployee(ServerRequest serverRequest) {
	   
	    return ServerResponse.ok().body(emp.getAll(), Employee.class);
	  }
  
  /**
   * Gets the id.
   *
   * @param serverRequest the server request
   * @return the id
   */
  public Mono<ServerResponse> getById(ServerRequest serverRequest) {
	   

	    return ServerResponse.ok().body(emp.getByid(serverRequest.pathVariable("id")), Employee.class);
	  }
  
  /**
   * Data.
   *
   * @param serverRequest the server request
   * @return the mono
   */
  public Mono<ServerResponse> postEmp(ServerRequest serverRequest) {
	  return serverRequest.bodyToMono(Employee.class).flatMap(empl->{
			return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).
		body(emp.saveEmp(empl), Employee.class);
		});
	  }
  
  /**
   * Updatadata.
   *
   * @param serverRequest the server request
   * @return the mono
   */
  public Mono<ServerResponse> updateById(ServerRequest serverRequest) {

	    return ServerResponse.ok().body(emp.update(serverRequest.pathVariable("id")),Employee.class);
	  }
  
  /**
   * Delete.
   *
   * @param serverRequest the server request
   * @return the mono
   */
  public Mono<ServerResponse> deleteById(ServerRequest serverRequest) {
	  Integer id=Integer.valueOf(serverRequest.pathVariable("id"));
	    return ServerResponse.ok().body(emp.deletebyId(serverRequest.pathVariable("id")),Void.class);
	  }

  /**
   * Delete all.
   *
   * @param serverRequest the server request
   * @return the mono
   */
  public Mono<ServerResponse> deleteAll(ServerRequest serverRequest)
  {
	  return ServerResponse.ok().body(emp.deleteAll(),Void.class);
  }
  
  
  /**
   * Gets the stream.
   *
   * @param serverRequest the server request
   * @return the stream
   */
  public Mono<ServerResponse> getStream(ServerRequest serverRequest)
  {
	  return ServerResponse.ok().body(emp.infiniteStream(),Long.class);
  }
}
