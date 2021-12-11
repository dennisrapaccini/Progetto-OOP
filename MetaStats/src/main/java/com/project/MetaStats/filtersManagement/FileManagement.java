package com.project.MetaStats.filtersManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.project.MetaStats.model.Location;

public class FileManagement {
	ArrayList<Location> cityList = new ArrayList<Location>();
	
	public FileManagement(){}
	
	/** Metodo che converte il contenuto del file JSON in ArrayList di Location
	 * 
	 * @return ArrayList<Location> 
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException
	 */
	public ArrayList<Location> getFile() throws FileNotFoundException, IOException, ParseException, JSONException{ //NON FUNZIONA
		JSONParser parser = new JSONParser();
		Object object = parser.parse(new FileReader("listaSplit (1).json"));
		JSONObject jsonObject = (JSONObject) object;
		JSONArray JSONcityList =  jsonObject.getJSONArray("Location"); //Problemi di casting tra JSON simple JSON normale
		if (JSONcityList != null) { 
			   for (int i=0;i<JSONcityList.length();i++){ 
				   Location loc;
				   String city = JSONcityList.getJSONObject(i).getString("City");
				   String province = JSONcityList.getJSONObject(i).getString("Province");
				   String region = JSONcityList.getJSONObject(i).getString("Region");
				   cityList.add(new Location(city,province,region));
			   } 
			} 
		
		System.out.println(cityList);
		return cityList;
		}
		
	}
	
