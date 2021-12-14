package com.project.MetaStats.exception;
/**
 * Classe contenente il metodo che genera un'eccezione quando un un parametro è mancante
 * 
 * @author Dennis Rapaccini
 * @author Cheikh Cisse
 *
 */
public class MissingParameterException extends Exception {

	private static final long serialVersionUID = 1L;
	
	String error;

	public String getError() {
		return error;
	}

	public MissingParameterException(String error) {
		super();
		this.error = error;
	}
	
	

}
