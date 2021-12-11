package com.project.MetaStats.filtersManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Vector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileManagement {
	
	public FileManagement(){}
	
	public JSONArray getFile() throws FileNotFoundException, IOException, ParseException{ //restituisce JSONArray JSON Simple
		JSONParser parser = new JSONParser();
		Object object = parser.parse(new FileReader("listaSplit (1).json"));
		JSONObject jsonObject = (JSONObject) object;
		JSONArray cityList = (JSONArray) jsonObject.get("Location");
		System.out.println(cityList);
		return (JSONArray)cityList;
		}
		
	}
	
