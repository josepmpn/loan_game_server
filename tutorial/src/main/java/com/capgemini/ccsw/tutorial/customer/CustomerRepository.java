package com.capgemini.ccsw.tutorial.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.capgemini.ccsw.tutorial.customer.model.Customer;

/**
 * @author jpratgin
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	/**
	 * Método para buscar un cliente por nombre.
	 * @return Customer
	 */
	Customer findByName(@Param("name") String name);

}
