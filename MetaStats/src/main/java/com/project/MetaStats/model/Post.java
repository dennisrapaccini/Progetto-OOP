package com.project.MetaStats.model;

/**
 * Questa classe rappresenta i post dell'utente facebbok
 * 
 * @author Cheikh Cisse
 * @author Dennis Rapaccini
 */
public class Post extends UserFacebook {
	
	private String message;
	private String CreatedTime;
	private String idPost;
	private String ID = getId()+"_"+idPost;
	
	/**
	 * Costruttore della classe post ereditato dalla superclasse UserFacebook
	 * 
	 * @param name
	 * @param surname
	 * @param id
	 * @param CreatedTime
	 * @param message
	 * @param ID
	 */
	public Post(String name, String surname, String id, String message, String CreatedTime, String ID) {
		super(name, surname, id);
		this.message = message;
		this.CreatedTime = CreatedTime;
		this.ID = ID;
	}
	
	/**
	 * Metodo che restituisce la descrizione del post(getter)
	 * 
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Metodo che setta la descrizione del post(setter)
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Metodo che restituisce la data di creazione del post(getter)
	 * 
	 * @return createdTime
	 */
	public String getCreatedTime() {
		return CreatedTime;
	}
	
	/**
	 * Metodo che setta la data di creazione del post(setter)
	 * 
	 * @param createdTime
	 */
	public void setCreatedTime(String createdTime) {
		CreatedTime = createdTime;
	}
	
	/**
	 * Metodo che restituisce l'id del post(getter)
	 * 
	 * @return idPost
	 */
	public String getIdPost() {
		return idPost;
	}
	
	/**
	 * Metodo che setta l'id del post(setter)
	 * 
	 * @param idPost
	 */
	public void setIdPost(String idPost) {
		this.idPost = idPost;
	}

	/**
	 * Override del metodo toString che restituisce l'oggetto Post in stringa
	 */
	@Override
	public String toString() {
		return "message: "+message+"/NcreatedTime: "+CreatedTime+"/nId: "+ID;
	}
	
}