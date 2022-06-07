package com.capgemini.ccsw.tutorial.loan;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.tutorial.config.mapper.BeanMapper;
import com.capgemini.ccsw.tutorial.loan.model.Loan;
import com.capgemini.ccsw.tutorial.loan.model.LoanDto;
import com.capgemini.ccsw.tutorial.loan.model.LoanSearchDto;
import com.capgemini.ccsw.tutorial.loan.service.LoanService;

/**
 * @author ccsw
 */
@RequestMapping(value = "/loan")
@RestController
@CrossOrigin(origins = "*")
public class LoanController {

	@Autowired
	LoanService loanService;

	@Autowired
	BeanMapper beanMapper;

	/**
	 * Método para recuperar un listrado filtrado de prestamos
	 * 
	 * @param idGame
	 * @param idCustomer
	 * @param loanDate
	 * @return
	 */
	@RequestMapping(path = "", method = RequestMethod.GET)
	public Page<LoanDto> find(@RequestParam(value = "idGame", required = false) Long idGame,
			@RequestParam(value = "idCustomer", required = false) Long idCustomer,
			@RequestParam(value = "loanDate", required = false) Date loanDate,
			@RequestParam(value = "pageNumber", required = true, defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", required = true, defaultValue = "5") int pageSize) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		if (idGame == null && idCustomer == null && loanDate == null) {
			LoanSearchDto dto = new LoanSearchDto();
			dto.setPageable(pageable);
			return this.beanMapper.mapPage(this.loanService.findPage(dto), LoanDto.class);
		} else {
			Page<Loan> loans = loanService.find(idGame, idCustomer, loanDate, pageable);
			return beanMapper.mapPage(loans, LoanDto.class);
		}
	}

	/**
	 * Método para crear un prestamo.
	 * 
	 * @param id
	 * @param dto
	 * @return
	 */
	@RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
	public Boolean save(@PathVariable(name = "id", required = false) Long id, @RequestBody LoanDto dto) {

		return loanService.save(id, dto);
	}

	/**
	 * Método para eliminar un prestamo
	 * 
	 * @param id PK de la entidad
	 */
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {

		this.loanService.delete(id);
	}

}
