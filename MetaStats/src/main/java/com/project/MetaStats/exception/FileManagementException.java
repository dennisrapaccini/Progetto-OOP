package com.project.MetaStats.exception;

/**
 * Classe contenente il metodo che genera un'eccezione quando occorre un errore nella lettura del file di database
 * 
 * @author Dennis Rapaccini
 * @author Cheikh Cisse
 *
 */
public class FileManagementException extends Exception {

	private static final long serialVersionUID = 1L;
	
	String error;

	public String getError() {
		return error;
	}

	public FileManagementException(String error) {
		super();
		this.error = error;
	}

	
	
	

}
