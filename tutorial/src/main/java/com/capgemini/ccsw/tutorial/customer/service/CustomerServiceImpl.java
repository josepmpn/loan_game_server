package com.capgemini.ccsw.tutorial.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.tutorial.customer.CustomerRepository;
import com.capgemini.ccsw.tutorial.customer.model.Customer;
import com.capgemini.ccsw.tutorial.customer.model.CustomerDto;

/**
 * @author jpratgin
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Customer get(Long id) {

		return this.customerRepository.findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Customer> findAll() {

		return (List<Customer>) this.customerRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws Exception
	 */
	@Override
	public boolean save(Long id, CustomerDto dto) {

		Customer customer = null;

		Customer foundCustomer = this.customerRepository.findByName(dto.getName());

		if (foundCustomer != null) {
			if (id == null) {
				return false;
			} else {
				if (foundCustomer.getId() != id) {
					return false;
				} else {
					customer = this.customerRepository.findById(id).orElse(null);
				}

			}

		} else {
			if (id == null) {
				customer = new Customer();
			} else {
				customer = this.customerRepository.findById(id).orElse(null);
			}
		}

		customer.setName(dto.getName());

		this.customerRepository.save(customer);

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long id) {

		this.customerRepository.deleteById(id);

	}
}
