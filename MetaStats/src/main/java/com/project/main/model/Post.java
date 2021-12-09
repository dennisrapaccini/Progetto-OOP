package com.project.main.model;

/**Questa classe rappresenta i post dell'utente facebbok
 * @author Cheikh
 * @author Dennis
 */

public class Post {
	
	/** Attributi relativi ai post dell'utente
	 */
	private String createdTime;
	private String message;
	
	/**Costruttore dell'oggetto post
	 * @param createdTime
	 * @param message
	 */
	public Post(String createdTime, String message) {
		super();
		this.createdTime = createdTime;
		this.message = message;
	}
	
	/**Metodo che restituisce il momento della creazione del post(getter)
	 * @return createdTime
	 */
	public String getCreatedTime() {
		return createdTime;
	}
	
	/**Metodo che setta il momento della creazione del post(setter)
	 * @param createdTime
	 */
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	
	/**Metodo che restituisce la descrizione del post(getter)
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	
	/**Metodo che setta la descrizione del post(setter)
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**Override del metodo toString che restituisce l'oggetto Post in stringa
	 */
	@Override
	public String toString() {
		return "Created time: "+createdTime+"/nDescription: "+message;
	}
	
}
