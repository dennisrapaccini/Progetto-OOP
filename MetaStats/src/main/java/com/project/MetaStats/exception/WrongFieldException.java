package com.project.MetaStats.exception;

/**
 * Classe contenente il metodo che genera un'eccezione quando un campo (es. type) non è valido
 * 
 * @author Dennis Rapaccini
 * @author Cheikh Cisse
 *
 */
public class WrongFieldException extends Exception {
	
		private static final long serialVersionUID = 1L;
		String error;

		public String getError() {
			return error;
		}

		public WrongFieldException(String error) {
			super();
			this.error = error;
		}
		
}


