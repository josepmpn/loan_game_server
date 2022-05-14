package com.capgemini.ccsw.tutorial.loan.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.capgemini.ccsw.tutorial.author.model.AuthorDto;
import com.capgemini.ccsw.tutorial.author.model.AuthorSearchDto;
import com.capgemini.ccsw.tutorial.customer.service.CustomerService;
import com.capgemini.ccsw.tutorial.game.GameRepository;
import com.capgemini.ccsw.tutorial.game.model.Game;
import com.capgemini.ccsw.tutorial.game.model.GameDto;
import com.capgemini.ccsw.tutorial.game.service.GameService;
import com.capgemini.ccsw.tutorial.loan.LoanRepository;
import com.capgemini.ccsw.tutorial.loan.model.Loan;
import com.capgemini.ccsw.tutorial.loan.model.LoanDto;
import com.capgemini.ccsw.tutorial.loan.model.LoanSearchDto;

/**
 * @author ccsw
 */
@Service
@Transactional
public class LoanServiceImpl implements LoanService {

	@Autowired
	LoanRepository loanRepository;

	@Autowired
	GameService gameService;

	@Autowired
	CustomerService customerService;

	/**
	 * {@inheritDoc}
	 */
	public Page<Loan> findPage(LoanSearchDto dto) {

		return this.loanRepository.findAll(dto.getPageable());

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<Loan> find(Long game, Long customer, Date loanDate, Pageable pageable) {

		List<Loan> listLoan = this.loanRepository.find(game, customer, loanDate, pageable);

		return new PageImpl<>(listLoan, pageable, listLoan.size());

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(Long id, LoanDto dto) {

		Loan loan = null;

		if (id == null) {
			// Comprobación de que un juego ya está prestado en una fecha de inicio de
			// prestamo.
			if (this.loanRepository.findGame(dto.getGame().getId(), dto.getStart()).size() > 0) {
				return false;
			} else {
				// Comprobación de que un cliente no tiene más de 2 juegos prestado en una fecha
				// de inicio de prestamo.
				if (this.loanRepository.findCustomer(dto.getCustomer().getId(), dto.getStart()).size() > 2) {
					return false;
				}
			}

			loan = new Loan();
		}

		loan.setCustomer(this.customerService.get(dto.getCustomer().getId()));
		loan.setGame(this.gameService.get(dto.getGame().getId()));
		loan.setStart(dto.getStart());
		loan.setEnd(dto.getEnd());

		this.loanRepository.save(loan);
		return true;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	public void delete(Long id) {
		this.loanRepository.deleteById(id);
	}

}
