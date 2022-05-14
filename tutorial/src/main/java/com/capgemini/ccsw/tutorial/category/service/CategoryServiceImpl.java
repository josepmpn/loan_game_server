package com.capgemini.ccsw.tutorial.category.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.tutorial.category.CategoryRepository;
import com.capgemini.ccsw.tutorial.category.model.Category;
import com.capgemini.ccsw.tutorial.category.model.CategoryDto;

/**
 * @author ccsw
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category get(Long id) {

		return this.categoryRepository.findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Category> findAll() {

		return (List<Category>) this.categoryRepository.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void save(Long id, CategoryDto dto) {

		Category categoria = null;

		if (id == null)
			categoria = new Category();
		else
			categoria = this.categoryRepository.findById(id).orElse(null);

		categoria.setName(dto.getName());

		this.categoryRepository.save(categoria);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long id) {

		this.categoryRepository.deleteById(id);

	}

}
