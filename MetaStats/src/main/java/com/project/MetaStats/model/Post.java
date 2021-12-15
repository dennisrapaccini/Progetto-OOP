package com.project.MetaStats.model;

/**Questa classe rappresenta i post dell'utente facebbok
 * @author Cheikh
 * @author Dennis
 */
public class Post extends User {

	/** Attributi relativi ai post dell'utente
	 */
	private String message;
	private String CreatedTime;
	private String idPost;
	private String ID = getId()+"_"+idPost;
	
	/**Costruttore dell'oggetto post
	 * @param createdTime
	 * @param message
	 */
	public Post(String id, String message, String CreatedTime, String ID) {
		super(id);
		this.message = message;
		this.CreatedTime = CreatedTime;
		this.ID = ID;
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
	
	public String getCreatedTime() {
		return CreatedTime;
	}

	public void setCreatedTime(String createdTime) {
		CreatedTime = createdTime;
	}
	
	public String getIdPost() {
		return idPost;
	}
	
	public void setIdPost(String idPost) {
		this.idPost = idPost;
	}


	/**Override del metodo toString che restituisce l'oggetto Post in stringa
	 */
	@Override
	public String toString() {
		return "/nmessage: "+message+"createdTime: "+CreatedTime+"Id: "+ID;
	}
	
}
