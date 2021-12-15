package com.project.MetaStats.service;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import com.project.MetaStats.model.Location;
import com.project.MetaStats.model.Post;

/**Interfaccia Service i cui metodi sono implementati in ServiceImpl
 * @author Cheikh
 * @author Dennis
 */
public interface Service {
	public abstract String getMessage_Post();
	public abstract JSONObject getPost_User();
	public abstract ArrayList<Post> allPosts() throws JSONException;
	public abstract JSONObject getPostsFromCity(String city) throws Exception;
	public abstract HashMap<Post, Location> PostLocationMapping() throws JSONException, FileNotFoundException, IOException, ParseException;
	public abstract JSONObject ranking(String type) throws FileNotFoundException, JSONException, IOException, ParseException;
	public abstract JSONArray getPostsFromProvince(String province) throws FileNotFoundException, JSONException, IOException, ParseException;
	public abstract JSONArray getPostsFromRegion(String region) throws FileNotFoundException, JSONException, IOException, ParseException;
}
