package com.project.MetaStats.exception;
/**
 * Classe contenente il metodo che genera un'eccezione quando una città non è presente nel database
 * 
 * @author Cheikh Cisse
 * @author Dennis Rapaccini
 */
public class NonExistingLocationException extends Exception {
	
	private static final long serialVersionUID = 1L;
	String error;
	
	public NonExistingLocationException(String error) {
		super();
		this.error = error;
	}
	
	public String getError() {
		return error;
	}
	
}
