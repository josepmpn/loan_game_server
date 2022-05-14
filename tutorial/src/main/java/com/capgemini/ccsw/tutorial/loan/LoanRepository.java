package com.capgemini.ccsw.tutorial.loan;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.capgemini.ccsw.tutorial.loan.model.Loan;

public interface LoanRepository extends CrudRepository<Loan, Long> {

	/**
	 * Método para recuperar un listado paginado de prestamos
	 * 
	 * @param page
	 * @return Page<Loan>
	 */
	Page<Loan> findAll(Pageable pageable);

	/**
	 * Método para recuperar un listado paginado de prestamos filtrados.
	 * 
	 * @param game
	 * @param customer
	 * @param loanDate
	 * @param pageable
	 * @return List<Loan>
	 */
	@Query("select l from Loan l where (:game is null or l.game.id = :game) and (:customer is null or l.customer.id = :customer) and (:loanDate is null or (:loanDate BETWEEN l.start AND l.end))")
	List<Loan> find(@Param("game") Long game, @Param("customer") Long customer, @Param("loanDate") Date loanDate,
			Pageable pageable);

	/**
	 * Método para recuperar un listado de prestamos vigentes de un juego
	 * 
	 * @param game
	 * @param start
	 * @return List<Loan>
	 */
	@Query("select l from Loan l where (:game is null or l.game.id = :game) and (:start is null or (:start BETWEEN l.start AND l.end))")
	List<Loan> findGame(@Param("game") Long game, @Param("start") Date start);

	
	/**
	 * Método para recuperar un listado de los prestamos vigentes de un cliente
	 * 
	 * @param customer
	 * @param start
	 * @return List<Loan>
	 */

	@Query("select l from Loan l where (:customer is null or l.customer.id = :customer) and (:start is null or (:start BETWEEN l.start AND l.end))")
	List<Loan> findCustomer(@Param("customer") Long customer, @Param("start") Date start);

}
