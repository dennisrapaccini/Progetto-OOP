package com.project.MetaStats.filtersManagement;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.project.MetaStats.exception.FileManagementException;
import com.project.MetaStats.model.Location;

public class FileManagement {
	
	private ArrayList<Location> cityList = new ArrayList<Location>();
	
	public FileManagement(){}
	
	public ArrayList<Location> getCityList() {
		return cityList;
	}

	public void setCityList(ArrayList<Location> cityList) {
		this.cityList = cityList;
	}
	
	/** Metodo che converte il contenuto del file JSON in ArrayList di Location
	 * 
	 * @return ArrayList<Location> 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 * @throws FileManagementException 
	 * @throws JSONException
	 *///aggiungere le altre eccezioni
	public void getFile() throws FileNotFoundException, IOException, ParseException, FileManagementException{ 
		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader("listaComuni.json"));
			JSONObject jsonObject = (JSONObject) object;
			JSONArray JSONcityList =  (JSONArray)jsonObject.get("Location");
			if (JSONcityList != null) { 
				   for (int i=0;i<JSONcityList.size();i++){ 
					   JSONObject obj = (JSONObject) JSONcityList.get(i);
					   String city = obj.get("City").toString();;
					   String province = obj.get("Province").toString();
					   String region = obj.get("Region").toString();
					   getCityList().add(new Location(city,province,region));
				   } 
			} 
		}
		catch(ParseException e) {
			throw new FileManagementException("ERRORE! Parsing errato! Controlla il contenuto del file");
		}
	}
}
	

