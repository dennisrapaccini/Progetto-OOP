package com.project.MetaStats.model;

/**
 * Questa classe rappresenta uno utente generale di un qualsiasi social
 * 
 * @author Cheikh Cisse
 * @author Dennis Rapaccini
 */
public class User {
	
	private String name;
	private String surname;
	
	/**
	 * Costruttore della classe User
	 * 
	 * @param name
	 * @param surname
	 */
	public User(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}
	
	/**
	 * Metodo che restituisce il nome dell'utente(getter)
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Metodo che setta il nome dell'utente(setter)
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Metodo che restituisce il cognome dell'utente(getter)
	 * 
	 * @return surname
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Metodo che setta il cognome dell'utente(setter)
	 * 
	 * @param surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
}