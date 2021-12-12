package com.project.MetaStats.filtersManagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.project.MetaStats.model.Location;

public class FileManagement {
	
	ArrayList<Location> cityList = new ArrayList<Location>();
	
	public FileManagement(){}
	
	/** Metodo che converte il contenuto del file listaComuni.json in ArrayList di Location
	 * 
	 * @return ArrayList<Location> 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException
	 */
	public ArrayList<Location> getFile() throws FileNotFoundException, IOException, ParseException, JSONException{
		JSONParser parser = new JSONParser();
		Object object = parser.parse(new FileReader("listaComuni.json"));
		JSONObject jsonObject = (JSONObject) object;
		JSONArray JSONcityList =  (JSONArray)jsonObject.get("Location");
		if (JSONcityList != null) { 
			   for (int i=0;i<(JSONcityList).size();i++){ 
				   JSONObject obj = (JSONObject) JSONcityList.get(i);
				   String city = (String) obj.get("City").toString();
				   String province = (String) obj.get("Province").toString();
				   String region = (String) obj.get("Region").toString();
				   cityList.add(new Location(city,province,region));
			   } 
		} 
		return cityList;
	}		
}
		

	
