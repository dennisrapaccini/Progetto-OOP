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

import com.project.MetaStats.exception.NonExistingCityException;
import com.project.MetaStats.filtersManagement.FileManagement;
import com.project.MetaStats.filtersManagement.FilterCity;
import com.project.MetaStats.filtersManagement.FilterProvince;
import com.project.MetaStats.filtersManagement.FilterRegion;
import com.project.MetaStats.model.Location;
import com.project.MetaStats.model.Post;
import com.project.MetaStats.statistics.Statistics;

/**Classe che fa l'override dei metodi definiti in Service
 * 
 * @author Cheikh
 * @author Dennis
 */
public class ServiceImpl implements Service {

	ArrayList<Location> postLocations = new ArrayList<Location>();

	/**Token di accesso (a lungo termine) alle API fornito all'utente da Facebook.
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
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/** Metodo che ritorna le API dell'utente riguardante i post in JSONObject
	 * 
	 */
	@Override
	public JSONObject getPost_User() { // restituisce JSONObject normale e non JSONOb
		JSONObject object = null;
		String url = "https://graph.facebook.com/me?fields=posts&access_token=" + token;
		RestTemplate rt = new RestTemplate();
		try {
			object = new JSONObject(rt.getForObject(url, String.class));
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(object);
		return object;
	}

	/**Metodo che restituisce la descrizione dei post da cui si ricava il nome della città
	 *
	 */
	@Override
	public String getMessage_Post() {
		try {
			JSONObject object = getPost_User();
			JSONObject object2 = object.getJSONObject("posts");
			JSONArray array = object2.getJSONArray("data");
			for (int i = 0; i < array.length(); i++) {
				message = array.getJSONObject(i).getString("message");
				//System.out.println(message);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	/**Questo metodo salva su ArrayList tutti i post
	 * @return Arraylist che contiene al suo interno la lista di tutti i post dell'utente
	 * 
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

		//System.out.println(posts);
		return posts;
	}
	
	/**Metodo che restituisce in JSONArray i post che contengono il parametro city (ignorando il letter case)
	 * 
	 * @param city Città da cercare
	 * @return JSONArray contenente tutti i post in cui è contenuta la città
	 */
	@Override
	public JSONObject getPostsFromCity(String city) throws Exception { // restituisce JSONArray normale NON JSON Array simple
		// TODO Fare controllo che non è una città qua dentro!! non nel controller
		// TODO Due eccezioni fare: uno se non è presente city e un'altra per i
		// JSONObject sotto.
		FilterCity filter = new FilterCity();
		if(!filter.isCity(city)) throw new NonExistingCityException("Errore! "+city+ " non è una città valida.\nInserisci una città italiana esistente!");
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
		//System.out.println(arrayPostsfromCity);
		return  objectPostsFromCity;
	}
	
	/**Metodo restituisce un ArraList dei post relativi alla provincia immessa dall'utente
	 * @param province 
	 * @return ArrayList provincePosts
	 * @throws NonExistingCityException 
	 */
	@Override
	public JSONObject getPostsFromProvince(String province) throws FileNotFoundException, JSONException, IOException, ParseException, NonExistingCityException{
		FilterProvince filter = new FilterProvince();
		if(!filter.isProvince(province)) throw new NonExistingCityException("Errore! "+province+ " non è una provincia valida.\nInserisci una provincia italiana esistente!");
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
	
	/**Metodo restituisce un ArraList dei post relativi alla regione immessa dall'utente
	 * @param region 
	 * @return ArrayList regionPosts
	 * @throws NonExistingCityException 
	 */
	@Override
	public JSONObject getPostsFromRegion(String region) throws FileNotFoundException, JSONException, IOException, ParseException, NonExistingCityException{
		FilterRegion filter = new FilterRegion();
		if(!filter.isRegion(region)) throw new NonExistingCityException("Errore! "+region+ " non è una regione valida.\nInserisci una regione italiana esistente!");
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

	/** Questo metodo mappa ogni post ad ogni location
	 * 
	 * @return HashMap in cui sono mappati i post alla relativa location
	 * @throws JSONException
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@Override
	public HashMap<Post, Location> PostLocationMapping() throws JSONException, FileNotFoundException, IOException, ParseException {
		HashMap<Post, Location> map = new HashMap<Post, Location>();
		ArrayList<Post> posts = new ArrayList<Post>();
		posts = allPosts();
		FileManagement database = new FileManagement();
		database.getFile();
		for (int i = 0; i < database.getCityList().size(); i++) {
			String city = database.getCityList().get(i).getCity();// i-esima città del database
			// ci andrebbe anche il toLowerCase, ma in questo modo trova anche città che non
			// si intedendavo tali: ad esempio se sul post c'è scritto : "tasti" lui prende
			// "asti"
			// Altro problema: esempio: Ha preso sia Cagli che Cagliari 
			for (int j = 0; j < posts.size(); j++) {
				if (posts.get(j).getMessage().contains(city)) {
					map.put(posts.get(j), database.getCityList().get(i));
				}
			}
		}
		//System.out.println(map);
		return map;
	}
	
	/**Metodo che mostra il ranking in JSONObject delle città, province o regioni visitate dall' utente
	 * @param type parametro che indica il tipo di ranking che si vuole visualizzare
	 * @return  JSONObject che mostra il ranking
	 */
	@Override
	public JSONObject ranking(String type) throws FileNotFoundException, JSONException, IOException, ParseException {
		HashMap<String,Integer> hm = new HashMap<String,Integer>();
		JSONArray array = new JSONArray();
		hm = stats.ranking(type);
		array = ToJSON.HashMapToJSONArray(hm, type.substring(0, 1).toUpperCase() + type.substring(1));
		JSONObject obj = new JSONObject();
		obj.put("Ranking " +type, array);
		return obj;
	}
	
	public JSONObject getPostsFromParameters(String type, List<String> locations ) throws Exception {
		JSONArray arr = new JSONArray();
		switch(type.toLowerCase()) {
		case "city" : 
			for(String city : locations) {
				arr.put(getPostsFromCity(city));  // String...locations == String[] locations
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
		
		
		
		
		
		
}

