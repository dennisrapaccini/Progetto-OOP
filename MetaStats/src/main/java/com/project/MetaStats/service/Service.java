package com.project.MetaStats.service;

import org.json.JSONObject;

/**Interfaccia Service i cui metodi sono implementati in ServiceImpl
 * @author Cheikh
 * @author Dennis
 */
public interface Service {
	
	public abstract JSONObject getPost_User();
	public abstract String getMessage_Post();
}
