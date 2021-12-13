package com.project.MetaStats.exception;

/**
 * Classe contenente il metodo che genera un'eccezione quando un parametro non è
 * permesso
 * 
 * @author Dennis Rapaccini
 * @author Cheikh Cisse
 *
 */
public class WrongParameterException extends Throwable {

	
	private static final long serialVersionUID = 1L;
	String e;

	public WrongParameterException(String e) {
		this.e = e;
	}

	public String getE() {
		return e;
	}
	

}
