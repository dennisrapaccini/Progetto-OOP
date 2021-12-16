package com.project.MetaStats.exception;

/**
 * Classe contenente il metodo che genera un'eccezione quando un parametro non è
 * permesso
 * 
 * @author Dennis Rapaccini
 * @author Cheikh Cisse
 *
 */
public class WrongParameterException extends Exception {

	private static final long serialVersionUID = 1L;
	String error;

	public WrongParameterException(String error, Throwable err) {
		super(error, err);
	}

	public String getError() {
		return error;
	}
	

}
