package com.project.MetaStats.model;

/**Questa classe rappresenta la pagina di un qualsiasi utente facebook
 * 
 * @author Cheikh
 * @author Dennis
 */
public class UserFacebook extends User{
	
	/**Attributi relativi all'utente
	 * 
	 */
	private String id;
	
	/**Costruttore della classe UserFacebook
	 * 
	 * @param name
	 * @param surname
	 * @param id
	 */
	public UserFacebook(String name, String surname, String id) {
		super(name, surname);
		this.id = id;
	}
	
	/**Metodo che restituisce l'id dell'utente
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**Metodo che setta l'id dell'utente
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**Override del metodo toString che restituisce l'oggetto UserFacebook in stringa
	 * 
	 */
	public String toString() {
		return "name: "+getName()+"/nsurname: "+getSurname()+"/nid: "+id;
	}
}
