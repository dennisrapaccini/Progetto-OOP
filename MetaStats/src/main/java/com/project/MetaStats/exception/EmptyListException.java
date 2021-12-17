package com.project.MetaStats.exception;

/**
 * Classe contenente il metodo che genera un'eccezione quando un elemento (ad esempio Array) è vuoto
 * 
 * @author Dennis Rapaccini
 * @author Cheikh Cisse
 *
 */
public class EmptyListException extends Exception {

	private static final long serialVersionUID = 1L;
	
	String error;

	public String getError() {
		return error;
	}

	public EmptyListException(String error) {
		super();
		this.error = error;
	}

	
	
	

}
