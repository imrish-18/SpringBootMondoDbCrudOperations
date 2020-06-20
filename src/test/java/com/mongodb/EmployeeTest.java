package com.mongodb;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.model.Employee;
import com.repo.EmployeeRepository;
import com.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
@TestInstance(Lifecycle.PER_CLASS)

@WebFluxTest
public class EmployeeTest {

	@InjectMocks 
	private EmployeeService employeeService;
	  
	  @Mock 
	  private EmployeeRepository employeeRepository;
	 @Autowired
	  private WebTestClient webTestClient;
	  
	  @BeforeAll 
	  public void setUp() 
	  { 
		  MockitoAnnotations.initMocks(this);
	  }
	  @Test 
	  public void getByIdTest() {
	  Mockito.when(employeeRepository.findById(Mockito.anyString())).thenReturn(Mono.
			  just(new Employee()));
			  
			  StepVerifier.create(employeeService.getByid(Mockito.anyString()))
			  .assertNext(res -> 
			  {assertNotNull(res,"value should not be null");
			  }).
			  verifyComplete();
			  
			  
			  }
	  @Test 
	  public void getAllTest() {
		  Mockito.when(employeeRepository.findAll()).thenReturn(Flux.just(new Employee())); 
		  
		  StepVerifier.create(employeeService.getAll()).
		  assertNext(res ->
		  assertNotNull(res)).
		  verifyComplete(); 
		  }
		  
		  @Test public void deleteById() {
		  Mockito.when(employeeRepository.deleteById(Mockito.anyString())).
		  thenReturn(Mono .empty()); 
		  StepVerifier.create(employeeService.
				  deletebyId(Mockito.anyString()).
		  doOnSubscribe(res -> 
		  { System.out.println("hello"); })).
		  verifyComplete(); 
		  }
		  
		  @Test 
		  public void saveTest() { 
			  Employee emp = new Employee();
		  emp.setCompany("gl"); 
		  emp.setId("3"); 
		  emp.setName("rish");
		  
		  Mockito.when(employeeRepository.save(emp)).
		  thenReturn(Mono.just(emp));
		  StepVerifier.create(employeeService.create(emp)).
		  assertNext(res ->
		  assertNotNull(res)).
		  verifyComplete(); }
	  
	  @Test 
	  public void updateById() {
	  
	  Mockito.when(employeeRepository.findById("4")); 
	  }
	  
	 

    @Test  
    public void getByIdUsingWebClientTest() { 
    	Flux<Long> mono= webTestClient.get(). 
	  uri("/stream").accept(MediaType.APPLICATION_JSON) 
	  .exchange().expectStatus().isOk() 
	  .returnResult(Long.class).getResponseBody(); 
	  StepVerifier.create(mono).expectSubscription() 
	  .expectNext(0L,1L,2L).thenCancel(). 
	  verify(); 
	  }
    @Test
    public void getAllByUsingWebTestClient() {
    	webTestClient.get().uri("/getdata")
    	.exchange()
    	.expectStatus().isOk()
    	.expectHeader().contentType(MediaType.APPLICATION_JSON)
    	
    	.expectBody(Employee.class);
    }
}
