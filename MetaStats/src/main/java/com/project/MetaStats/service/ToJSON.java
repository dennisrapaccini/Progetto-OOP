package com.project.MetaStats.service;

import java.util.ArrayList;

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
	 * @return JSONArray array di ritorno
	 * @throws JSONException
	 */
	public JSONArray toJSONArray(ArrayList<Post> p) throws JSONException {
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		for(int i = 0; i < p.size(); i++) {
			obj.put("Created_time", p.get(i).getCreatedTime());
			obj.put("Message", p.get(i).getMessage());
			arr.put(obj);
			
		}
		System.out.println(p);
		return arr;
	}
}
