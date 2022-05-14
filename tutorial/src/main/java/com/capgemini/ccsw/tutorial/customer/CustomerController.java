package com.capgemini.ccsw.tutorial.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.tutorial.config.mapper.BeanMapper;
import com.capgemini.ccsw.tutorial.customer.model.CustomerDto;
import com.capgemini.ccsw.tutorial.customer.service.CustomerService;

/**
 * @author jpratgin
 */
@RequestMapping(value = "/customer")
@RestController
@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@Autowired
	BeanMapper beanMapper;

	/**
	 * Método para recuperar todas los clientes
	 * 
	 * @return
	 */
	@RequestMapping(path = "", method = RequestMethod.GET)
	public List<CustomerDto> findAll() {

		return this.beanMapper.mapList(this.customerService.findAll(), CustomerDto.class);
	}

	/**
	 * Método para crear o actualizar un cliente
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
	public boolean save(@PathVariable(name = "id", required = false) Long id, @RequestBody CustomerDto dto) {

		return this.customerService.save(id, dto);
	}

	/**
	 * Método para borrar datos de un cliente
	 * 
	 * @param id
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {

		this.customerService.delete(id);

	}
}
