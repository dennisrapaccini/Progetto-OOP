package com.project.MetaStats.service;

import java.io.FileNotFoundException;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import com.project.MetaStats.exception.EmptyListException;
import com.project.MetaStats.exception.NonExistingLocationException;
import com.project.MetaStats.exception.WrongFieldException;
import com.project.MetaStats.exception.WrongParameterException;
import com.project.MetaStats.model.Location;
import com.project.MetaStats.model.Post;

/**Interfaccia Service i cui metodi sono implementati in ServiceImpl
 * @author Cheikh
 * @author Dennis
 */
public interface Service {
	public abstract JSONObject getPost_User();
	public abstract ArrayList<Post> allPosts() throws JSONException;
	public abstract JSONObject getPostsFromCity(String city) throws NonExistingLocationException, FileNotFoundException, IOException, ParseException, JSONException;
	public abstract HashMap<Post, Location> PostLocationMapping() throws JSONException, FileNotFoundException, IOException, ParseException;
	public abstract JSONObject ranking(String type, String initialDate, String finalDate) throws FileNotFoundException, JSONException, IOException, ParseException, WrongParameterException, WrongFieldException, EmptyListException;
	public abstract JSONObject getPostsFromProvince(String province) throws FileNotFoundException, JSONException, IOException, ParseException, NonExistingLocationException;
	public abstract JSONObject getPostsFromRegion(String region) throws FileNotFoundException, JSONException, IOException, ParseException, NonExistingLocationException;
	public abstract JSONObject getPostsFromParameters(String type, List<String> locations) throws Exception ;
	public abstract JSONObject getCityFromPosts(String type) throws FileNotFoundException, JSONException, IOException, ParseException;
}
