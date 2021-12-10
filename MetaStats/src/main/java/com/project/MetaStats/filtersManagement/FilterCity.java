package com.project.MetaStats.filtersManagement;

import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;
import com.project.MetaStats.model.Post;
import com.project.MetaStats.service.ServiceImpl;

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
	 * @param Città da cercare
	 * @return true se la città è presente tra i post, false altrimenti.
	 */
	public JSONArray getPostsfromCity(String city) throws Exception { // TODO Due eccezioni fare: uno se non è presente
																		// city e un'altra per i JSONObject sotto.

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
}
