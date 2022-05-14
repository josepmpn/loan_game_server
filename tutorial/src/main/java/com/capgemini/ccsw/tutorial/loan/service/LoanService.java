package com.capgemini.ccsw.tutorial.loan.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.capgemini.ccsw.tutorial.loan.model.Loan;
import com.capgemini.ccsw.tutorial.loan.model.LoanDto;
import com.capgemini.ccsw.tutorial.loan.model.LoanSearchDto;

/**
 * @author jpratgin
 */
public interface LoanService {

	/**
	 * Método para recuperar un listado paginado de prestamos
	 * 
	 * 
	 * @param dto
	 * @return
	 */
	public Page<Loan> findPage(LoanSearchDto dto);

	/**
	 * Recupera los prestamos filtrando opcionalmente por juego y/o cliente y/o
	 * fecha.
	 * 
	 * @param title
	 * @param idCategory
	 * @return
	 */
	Page<Loan> find(Long idGame, Long idCustomer, Date loanDate, Pageable pageable);
	

	/**
	 * Guarda un prestamo.
	 * 
	 * @param id
	 * @param dto
	 */
	boolean save(Long id, LoanDto dto);
	
	
	/**
	 * Borrar un prestamo
	 * 
	 * @param id
	 *
	 */
	void delete(Long id);


}
