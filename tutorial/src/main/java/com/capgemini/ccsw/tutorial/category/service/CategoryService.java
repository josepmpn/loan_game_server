package com.capgemini.ccsw.tutorial.category.service;

import java.util.List;

import com.capgemini.ccsw.tutorial.category.model.Category;
import com.capgemini.ccsw.tutorial.category.model.CategoryDto;

/**
 * @author ccsw
 *
 */
public interface CategoryService {
	
	/** 
	 * Método para recuperar una Category
	 * @param id
	 * @return
	 */
	Category get(Long id);

	/**
	 * Método para recuperar todas las Category
	 * 
	 * @return
	 */
	List<Category> findAll();

	/**
	 * Método para crear o actualizar una Category
	 * 
	 * @param dto
	 * @return
	 */
	void save(Long id, CategoryDto dto);

	/**
	 * Método para borrar una Category
	 * 
	 * @param id
	 */
	void delete(Long id);
	
}
