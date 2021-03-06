package com.project.MetaStats.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.project.MetaStats.exception.EmptyListException;
import com.project.MetaStats.exception.FileManagementException;
import com.project.MetaStats.exception.NonExistingLocationException;
import com.project.MetaStats.exception.WrongFieldException;
import com.project.MetaStats.exception.WrongParameterException;
import com.project.MetaStats.filtersManagement.FileManagement;
import com.project.MetaStats.filtersManagement.FilterCity;
import com.project.MetaStats.filtersManagement.FilterProvince;
import com.project.MetaStats.filtersManagement.FilterRegion;
import com.project.MetaStats.model.Location;
import com.project.MetaStats.model.Post;
import com.project.MetaStats.statistics.Statistics;

/**
 * Classe che esegue l'override dei metodi definiti in Service
 * 
 * @author Cheikh Cisse
 * @author Dennis Rapaccini
 */
public class ServiceImpl implements Service {

	ArrayList<Location> postLocations = new ArrayList<Location>();

	/**
	 * Token di accesso (a lungo termine) alla API di Facebook fornito all'utente dallo stesso.
	 * 
	 * L'access token è stato diviso per evitare invalidazioni da parte del
	 * controllo di Facebook su GitHub.
	 */
	private String preToken = "EAANA9YBtvWIBAGv7ZCzBbjKdVBLNXd1CipYksDZC";
	private String token = preToken
							+ "Ogk488ah2zLZAsg1lRo8nWaPfS7XRZBGvZAtHYQ92p5Vt3ZC8dL4tjF0FqCJaG5iMzUw0hlYtH"
							+ "K0oN90mgYFiswcJ5B0VNBoZB2kw4cwOtes51wsxOYJw1wHZB7OtvKAHNWmp4DEWoQ98Q64";
	
	private String Id="107864155075941";
	private String message;
	private Statistics stats = new Statistics();
	
	/**
	 * Metodo che restituisce la descrizione del post(getter)
	 * 
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Metodo che setta la descrizione dei post(setter)
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Metodo che ritorna tutti i post dell'utente in JSONObject
	 * 
	 * @return JSONObject contenente tutti i post dell'utente Facebook
	 */
	@Override
	public JSONObject getPost_User() { 
		JSONObject object = null;
		String url = "https://graph.facebook.com/me?fields=posts&access_token=" + token;
		RestTemplate rt = new RestTemplate();
		try {
			object = new JSONObject(rt.getForObject(url, String.class));
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * Metodo che salva su ArrayList tutti i post dell'utente
	 * 
	 * @return Arraylist che contiene la lista di tutti i post dell'utente
	 * @throws JSONException
	 */
	@Override
	public  ArrayList<Post> allPosts() throws JSONException {
		ArrayList<Post> posts = new ArrayList<Post>();
		JSONObject object = getPost_User();
		JSONObject object2 = object.getJSONObject("posts");
		JSONArray data = object2.getJSONArray("data");
		for (int i = 0; i < data.length(); i++) {
			String createdTime = data.getJSONObject(i).getString("created_time");
			String message = data.getJSONObject(i).getString("message");
			String id = data.getJSONObject(i).getString("id");
			posts.add(new Post(null,null,Id, message, createdTime, id));
		}
		return posts;
	}
	
	/**
	 * Metodo che restituisce un JSONObject contenente i post relativi alla città in argomento (ignorando il letter case)
	 * 
	 * @param city Città da filtrare
	 * @return JSONObject contenente tutti i post in cui è contenuta la città
	 * @throws JSONException 
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws NonExistingLocationException
	 * @throws FileManagementException 
	 */
	@Override
	public JSONObject getPostsFromCity(String city) throws NonExistingLocationException, FileNotFoundException, IOException, ParseException, JSONException, FileManagementException {
		FilterCity filter = new FilterCity();
		if(!filter.isCity(city)) throw new NonExistingLocationException("Errore! "+city+ " non è una città valida.\nInserisci una città italiana esistente!");
		JSONObject objectPostsFromCity = new JSONObject();
		ServiceImpl serviceImpl = new ServiceImpl();
		JSONObject object = serviceImpl.getPost_User();
		JSONObject object2 = object.getJSONObject("posts");
		JSONArray array = object2.getJSONArray("data");
		JSONArray arrayPostsfromCity = new JSONArray();
		for (int i = 0; i < array.length(); i++) {
			String message = array.getJSONObject(i).getString("message").toLowerCase();
			if (message.contains(city.toLowerCase())) {
				arrayPostsfromCity.put(array.getJSONObject(i));
			} 
		}
		objectPostsFromCity.put("Posts in "+city, arrayPostsfromCity);
		return  objectPostsFromCity;
	}
	
	/**
	 * Metodo che restituisce un JSONObject contenente i post relativi alla provincia in argomento (ignorando il letter case)
	 * @param province Provincia da filtrare 
	 * @return JSONObject contenente tutti i post in cui è contenuta la provincia
	 * @throws FileNotFoundException
	 * @throws JSONException
	 * @throws IOException
	 * @throws ParseException
	 * @throws NonExistingLocationException
	 * @throws FileManagementException 
	 */
	@Override
	public JSONObject getPostsFromProvince(String province) throws FileNotFoundException, JSONException, IOException, ParseException, NonExistingLocationException, FileManagementException{
		FilterProvince filter = new FilterProvince();
		if(!filter.isProvince(province)) throw new NonExistingLocationException("Errore! "+province+ " non è una provincia valida.\nInserisci una provincia italiana esistente!");
		HashMap<Post,Location> map = new HashMap<Post,Location>();
		ArrayList<Post> provincePosts = new ArrayList<Post>();
		JSONObject obj = new JSONObject();
		map = PostLocationMapping();
		for (Entry<Post, Location> me : map.entrySet()) {
			if((me.getValue().getProvince().equalsIgnoreCase(province))){
				provincePosts.add(me.getKey());
			}
        }
		obj.put("Posts in: "+province,ToJSON.ArrayListToJSONArray(provincePosts));
		return obj;
	}
	
	/**
	 * Metodo che restituisce un JSONObject contenente i post relativi alla regione in argomento (ignorando il letter case)
	 * 
	 * @param region Regione da filtrare
	 * @return JSONObject contenente tutti i post in cui è contenuta la regione
	 * @throws NonExistingLocationException 
	 * @throws JSONException
	 * @throws IOException
	 * @throws ParseException
	 * @throws FileManagementException
	 */
	@Override
	public JSONObject getPostsFromRegion(String region) throws FileNotFoundException, JSONException, IOException, ParseException, NonExistingLocationException, FileManagementException{
		FilterRegion filter = new FilterRegion();
		if(!filter.isRegion(region)) throw new NonExistingLocationException("Errore! "+region+ " non è una regione valida.\nInserisci una regione italiana esistente!");
		HashMap<Post,Location> map = new HashMap<Post,Location>();
		ArrayList<Post> regionPosts = new ArrayList<Post>();
		JSONObject obj = new JSONObject();
		map = PostLocationMapping();
		for (Entry<Post, Location> me : map.entrySet()) {
			if((me.getValue().getRegion().equalsIgnoreCase(region))){
				regionPosts.add(me.getKey());
			}
    	}
		obj.put("Posts in: "+region,ToJSON.ArrayListToJSONArray(regionPosts));
		return obj;
	}
	
	/**
	 * Metodo che restituisce i post dell'utente a seconda del parametro immesso
	 * 
	 * @param type stringa che indica il tipo di filtro che si vuole eseguire
	 * @param locations lista di stringa che mi permette di filtrare anche con parametri variabili
	 * @return JSONObject oggetto rappresentativo di ciò che è stato filtrato 
	 */
	public JSONObject getPostsFromParameters(String type, List<String> locations ) throws Exception {
		JSONArray arr = new JSONArray();
		switch(type.toLowerCase()) {
		case "city" : 
			for(String city : locations) {
				arr.put(getPostsFromCity(city));  
			}
			break;
		case "province" : 
			for(String province : locations) {
				arr.put(getPostsFromProvince(province));
			}
			break;
		case "region" : 
			for(String region : locations) {
				arr.put(getPostsFromRegion(region));
			}
			break;
		}
		JSONObject obj = new JSONObject();
		obj.put(type,arr);
		return obj;
	}

	/** 
	 * Metodo che mappa ogni post ad ogni location
	 * 
	 * @return HashMap in cui sono mappati i post alla relativa location
	 * @throws JSONException
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws FileManagementException 
	 */
	@Override
	public HashMap<Post, Location> PostLocationMapping() throws JSONException, FileNotFoundException, IOException, ParseException, FileManagementException {
		HashMap<Post, Location> map = new HashMap<Post, Location>();
		ArrayList<Post> posts = new ArrayList<Post>();
		posts = allPosts();
		FileManagement database = new FileManagement();
		database.getFile();
		for (int i = 0; i < database.getCityList().size(); i++) {
			String city = database.getCityList().get(i).getCity();
			for (int j = 0; j < posts.size(); j++) {
				if (posts.get(j).getMessage().contains(city)) {
					map.put(posts.get(j), database.getCityList().get(i));
				}
			}
		}
		return map;
	}
	
	/**
	 * Override del metodo ranking()
	 * 
	 * @param type Parametro che indica il tipo di ranking che si vuole visualizzare (city, province o region)
	 * @return JSONObject che mostra il ranking
	 * @throws WrongParameterException 
	 * @throws WrongFieldException 
	 * @throws FileNotFoundException
	 * @throws JSONException
	 * @throws IOException
	 * @throws ParseException
	 * @throws EmptyListException 
	 * @throws FileManagementException 
	 */
	@Override
	public JSONObject ranking(String type, String initialDate, String finalDate) throws FileNotFoundException, JSONException, IOException, ParseException, WrongParameterException, WrongFieldException, EmptyListException, FileManagementException {
		HashMap<String,Integer> hm = new HashMap<String,Integer>();
		JSONArray array = new JSONArray();
		hm = stats.ranking(type, initialDate, finalDate);
		array = ToJSON.HashMapToJSONArray(hm, type.substring(0, 1).toUpperCase() + type.substring(1));
		JSONObject obj = new JSONObject();
		obj.put("Ranking " +type, array);
		return obj;
	}
	
	/**
	 * Metodo che restituisce le città, province e regioni in cui l'utente è stato a seconda del parametro immesso dall' utente
	 * 
	 * @param type tipo di filtro che si vuole eseguire
	 * @return JSONObject che mostra il filtraggio
	 * @throws FileManagementException 
	 * @throws WrongFieldException 
	 */
	@Override
	public JSONObject getLocationFromPosts(String type) throws FileNotFoundException, JSONException, IOException, ParseException, FileManagementException, WrongFieldException {
		JSONArray arr = new JSONArray();
		switch(type.toLowerCase()) {
		case "city" :
			FilterCity filter = new FilterCity();
			filter.filter();
			arr.put(filter.filter());
			break;
		case "province" : 
			FilterProvince filter2= new FilterProvince();
			filter2.filter();
			arr.put(filter2.filter());
			break;
		case "region" :
			FilterRegion filter3= new FilterRegion();
			filter3.filter();
			arr.put(filter3.filter());
			break;
			
		default : throw new WrongFieldException("ERRORE! Inserisci un tipo valido: solo city, province o region sono ammessi");
		
		}
		JSONObject obj = new JSONObject();
		obj.put(type,arr);
		return obj;
	}
	
}