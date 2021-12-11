package com.project.MetaStats.filtersManagement;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONArray;
import com.project.MetaStats.model.Post;
import com.project.MetaStats.service.ServiceImpl;
import org.json.simple.*;

public class FilterCity extends Filter {

	ServiceImpl service = new ServiceImpl();
	String prova = service.getMessage_Post();

	public FilterCity() {
	
	}

	private String city;

	public FilterCity(String city) {
		super();
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String filter() {
		return city;
	}

	/**
	 * Metodo che restituisce in JSONArray i post che contengono il parametro city.
	 * 
	 * @param city Città da cercare
	 * @return JSONArray contenente tutti i post in cui è contenuta la città
	 */
	public JSONArray getPostsfromCity(String city) throws Exception { //restituisce JSONArray normale NON JSON Array JSON Simple
		
		// TODO Due eccezioni fare: uno se non è presente city e un'altra per i JSONObject sotto.

		ServiceImpl serviceImpl = new ServiceImpl();
		JSONObject object = serviceImpl.getPost_User();
		JSONObject object2 = object.getJSONObject("posts");
		JSONArray array = object2.getJSONArray("data");
		JSONArray ArrayPostsfromCity = new JSONArray();

		for (int i = 0; i < array.length(); i++) {
			String message = array.getJSONObject(i).getString("message");
			if (message.contains(city)) {
				ArrayPostsfromCity.put(array.getJSONObject(i));
			}
		}
		System.out.println(ArrayPostsfromCity);
		return ArrayPostsfromCity;
	}
	
	
	
	/*HashMap<JSONObject, JSONObject> mapPostCity = new HashMap<>(); // lo faccio in JSONObject, però allora i tipi Post e Location a che servono?
	ServiceImpl serviceImpl = new ServiceImpl();
	FileManagement database = new FileManagement();
	JSONObject object = serviceImpl.getPost_User();
	try {
	JSONObject object2 = object.getJSONObject("posts");
	JSONArray array = object2.getJSONArray("data");
	for (int i = 0; i < array.length(); i++) {
		String message = (database.getFile()).getJSONObject(i).getString("City");
		if (message.contains(database.getFile().getJSONObject(i))) 
			
		}
	}
	}*/
	
	
}
