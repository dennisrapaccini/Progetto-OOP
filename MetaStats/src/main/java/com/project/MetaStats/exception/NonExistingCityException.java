package com.project.MetaStats.exception;
/**
 * Classe contenente il metodo che genera un'eccezione quando una città non è presente nel database
 * 
 * @author Cheikh Cisse
 * @author Dennis Rapaccini
 */
public class NonExistingCityException extends Exception {

	
	private static final long serialVersionUID = 1L;
	String error;

	public NonExistingCityException(String error) {
		super();
		this.error = error;
	}

	public String getError() {
		return error;
	}
	

}
