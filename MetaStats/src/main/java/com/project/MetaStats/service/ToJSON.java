package com.project.MetaStats.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.project.MetaStats.model.Post;

/**Classe che converte un ArrayList in JSONArray
 * @author Cheikh
 * @author Dennis
 *
 */
public class ToJSON {
	
	/**Metodo che converte l'ArrayList in JSONArray
	 * @param p
	 * @return JSONArray
	 * @throws JSONException
	 */
	public static JSONArray ArrayListToJSONArray(ArrayList<Post> p) throws JSONException {
		JSONArray arr = new JSONArray();
		for(int i = 0; i < p.size(); i++) {  
			JSONObject obj = new JSONObject();
			obj.put("Created Time", p.get(i).getCreatedTime());
			obj.put("Message", p.get(i).getMessage());
			System.out.println();
			arr.put(obj);
		}
		return arr;
	}
	
	/**Metodo che converte un HashMap in JSONObject o JSONArray
	 * @throws JSONException
	 * 
	 */
	public static JSONArray HashMapToJSONArray(HashMap<String, Integer> hm, String type) throws JSONException {
		//poi passare a JSONObject su serviceImpl
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
