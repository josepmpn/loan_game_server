package com.capgemini.ccsw.tutorial.loan.model;

import java.util.Date;

import com.capgemini.ccsw.tutorial.customer.model.CustomerDto;
import com.capgemini.ccsw.tutorial.game.model.GameDto;

/**
* @author jpratgin
*/
public class LoanDto {

    private Long id;

    private GameDto game;

    private CustomerDto customer;
    
    private Date start;
    
    private Date end;

    /**
    * @return id
    */
    public Long getId() {

        return this.id;
    }

    /**
    * @param id new value of {@link #getId}.
    */
    public void setId(Long id) {

        this.id = id;
    }

	/**
	 * @return the game
	 */
	public GameDto getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(GameDto game) {
		this.game = game;
	}

	/**
	 * @return the customer
	 */
	public CustomerDto getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	/**
	 * @return the start
	 */
	public Date getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Date start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(Date end) {
		this.end = end;
	}

    
}

