package com.project.MetaStats.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.project.MetaStats.model.Post;

/**
 * Classe che converte un ArrayList in JSONArray
 * 
 * @author Cheikh Cisse
 * @author Dennis Rapaccini
 *
 */
public class ToJSON {
	
	/**
	 * Metodo che converte un ArrayList di Post in un JSONArray
	 * 
	 * @param p ArrayList di Post
	 * @return JSONArray
	 * @throws JSONException
	 */
	public static JSONArray ArrayListToJSONArray(ArrayList<Post> p) throws JSONException {
		JSONArray arr = new JSONArray();
		for(int i = 0; i < p.size(); i++) {  
			JSONObject obj = new JSONObject();
			obj.put("Created Time", p.get(i).getCreatedTime());
			obj.put("Message", p.get(i).getMessage());
			arr.put(obj);
		}
		return arr;
	}
	
	/**
	 * Metodo che converte un HashMap con key String e value Integer in JSONObject o JSONArray
	 * 
	 * @param hm HashMap con key String e value Integer
	 * @param type stringa
	 * @throws JSONException
	 * @return JSONArray
	 */
	public static JSONArray HashMapToJSONArray(HashMap<String, Integer> hm, String type) throws JSONException {
		JSONArray arr = new JSONArray();
		for(Entry<String, Integer> set : hm.entrySet()) {
			JSONObject object = new JSONObject();
			object.put("Occurences", set.getValue());
			object.put(type, set.getKey());
			arr.put(object);
		}
		return arr;
	}
	
}