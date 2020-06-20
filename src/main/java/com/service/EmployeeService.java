package com.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Employee;
import com.repo.EmployeeRepository;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * The Class EmployeeService.
 */
@Service
public class EmployeeService {
	
     /** The rep. */
     @Autowired
	private EmployeeRepository rep;
     
/**
 * save employee.
 *
 * @param emp1 the emp 1
 * @return the mono
 */
public  Mono<Employee> saveEmp(Employee emp1)
{
	
	 return rep.save(emp1);
}

/**
 * Get emp.
 *
 * @return the flux
 */
public Flux<Employee> get()

{
	return rep.findAll().delayElements(Duration.ofSeconds(4));
}

/**
 * Get the all emp
 *
 * @return the all
 */
public Flux<Employee> getAll()
{
	return rep.findAll();
}

/**
 * Get the byid.
 *
 * @param id the id
 * @return the byid
 */
public Mono<Employee> getByid(String id)
{
	return rep.findById(id);
}

/**
 * Savedata.
 *
 * @param e the e
 * @return the mono
 */
public Mono<Employee> savedata(Employee e)
{
	 rep.save(e).subscribe(System.out::println);
	
return rep.save(e);
	

}

/**
 * Deleteby id.
 *
 * @param id the id
 * @return the mono
 */
public Mono<Void> deletebyId(String id)
{
	return rep.deleteById(id);
		/*
		 * return rep.findById(id).map(t-> { return t.getId(); }).flatMap(d->{ return
		 * rep.deleteById(d); });
		 */


}

/**
 * Update.
 *
 * @param id the id
 * @return the mono
 */
public Mono<Employee> update(String id)
{
	return rep.findById(id).map(obj->{
	obj.setName("jason");
	obj.setCompany("gl");
	return obj;
	}).flatMap(ob->{
		
		return rep.save(ob);
	});
	
}

/**
 * Delete all.
 *
 * @return the mono
 */
public Mono<Void> deleteAll()
{
	return rep.deleteAll();
}

/**
 * Infinite stream.
 *
 * @return the flux
 */
public Flux<Long> infiniteStream()
{
	return Flux.interval(Duration.ofSeconds(1)).log();
}

}
