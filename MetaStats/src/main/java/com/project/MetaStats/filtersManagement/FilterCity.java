package com.project.MetaStats.filtersManagement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import com.project.MetaStats.model.Post;
import com.project.MetaStats.service.ServiceImpl;
import com.project.MetaStats.model.Location;
import org.json.simple.*;
import org.json.simple.parser.ParseException;

public class FilterCity extends Filter {

	ServiceImpl service = new ServiceImpl();
	String prova = service.getMessage_Post();
	FileManagement database = new FileManagement();

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
	 * Metodo che controlla se la città da parametro è presente nel database
	 * 
	 * @param city La città da controllare
	 * @return true se la città è presente, false altrimenti
	 * @throws JSONException
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public boolean isCity(String city) throws FileNotFoundException, IOException, ParseException, JSONException {
		// Bisogna scegliere se contains o equals: se si
		// sceglie contains, il metodo rileva anche singole
		// lettere come città. Se si sceglie il secondo, se
		// una città ha due parole (es. Civitanova Marche) e
		// l'utente scrive solo Civitanova, non la prende
		boolean isCity = false;
		database.getFile();
		for (int i = 0; i < database.cityList.size(); i++) {
			if (database.cityList.get(i).getCity().contains(city)) { 
				isCity = true;
			}
		}
		return isCity;
	}

	/**
	 * Metodo che restituisce in JSONArray i post che contengono il parametro city.
	 * 
	 * @param city Città da cercare
	 * @return JSONArray contenente tutti i post in cui è contenuta la città
	 */
	public JSONArray getPostsfromCity(String city) throws Exception { // restituisce JSONArray normale NON JSON Array
																		// JSON Simple

		// TODO Due eccezioni fare: uno se non è presente city e un'altra per i
		// JSONObject sotto.

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

	/*
	 * HashMap<JSONObject, JSONObject> mapPostCity = new HashMap<>(); // lo faccio
	 * in JSONObject, però allora i tipi Post e Location a che servono? ServiceImpl
	 * serviceImpl = new ServiceImpl(); FileManagement database = new
	 * FileManagement(); JSONObject object = serviceImpl.getPost_User(); try {
	 * JSONObject object2 = object.getJSONObject("posts"); JSONArray array =
	 * object2.getJSONArray("data"); for (int i = 0; i < array.length(); i++) {
	 * String message = (database.getFile()).getJSONObject(i).getString("City"); if
	 * (message.contains(database.getFile().getJSONObject(i)))
	 * 
	 * } } }
	 */

}
