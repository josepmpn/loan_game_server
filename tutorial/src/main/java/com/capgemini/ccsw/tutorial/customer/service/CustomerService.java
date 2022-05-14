package com.capgemini.ccsw.tutorial.customer.service;

import java.util.List;

import com.capgemini.ccsw.tutorial.customer.model.Customer;
import com.capgemini.ccsw.tutorial.customer.model.CustomerDto;

/**
 * @author jpratgin
 *
 */
public interface CustomerService {

	
	/**
	 * {@inheritDoc}
	 */
	Customer get(Long id);
	/**
	 * Método para recuperar todos los clientes
	 * 
	 * @return
	 */
	List<Customer> findAll();

	/**
	 * Método para crear o actualizar un Cliente
	 * 
	 * @param dto
	 * @return
	 * @throws Exception 
	 */
	boolean save(Long id, CustomerDto dto);

	/**
	 * Método para borrar un cliente
	 * 
	 * @param id
	 */
	void delete(Long id);


}
