package com.project.MetaStats.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

/**Interfaccia Service i cui metodi sono implementati in ServiceImpl
 * @author Cheikh
 * @author Dennis
 */
public interface Service {
	
	public abstract JSONObject getPost_User();
	public abstract void allPosts() throws JSONException;
	public JSONObject getPostsfromCity(String city) throws Exception;
	public void PostLocationMap() throws JSONException, FileNotFoundException, IOException, ParseException;
}
