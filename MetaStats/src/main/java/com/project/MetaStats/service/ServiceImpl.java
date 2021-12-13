package com.project.MetaStats.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.project.MetaStats.filtersManagement.FileManagement;
import com.project.MetaStats.model.Location;
import com.project.MetaStats.model.Post;
import com.project.MetaStats.model.SuperPost;

/**Classe che fa l'override dei metodi definiti in Service
 * 
 * @author Cheikh
 * @author Dennis
 */
public class ServiceImpl implements Service {

	ArrayList<Post> posts = new ArrayList<Post>();
	ArrayList<Location> postLocations = new ArrayList<Location>();
	HashMap<Post, Location> map = new HashMap<Post, Location>();

	/**Token di accesso (a lungo termine) alle API fornito all'utente da Facebook.
	 * 
	 * L'access token è stato diviso per evitare invalidazioni da parte del
	 * controllo di Facebook su GitHub.
	 */
	private String preToken = "EAANA9YBtvWIBAGv7ZCzBbjKdVBLNXd1CipYksDZC";
	private String token = preToken
			+ "Ogk488ah2zLZAsg1lRo8nWaPfS7XRZBGvZAtHYQ92p5Vt3ZC8dL4tjF0FqCJaG5iMzUw0hlYtHK0oN90mgYFiswcJ5B0VNBoZB2kw4cwOtes51wsxOYJw1wHZB7OtvKAHNWmp4DEWoQ98Q64";

	private String message;

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
		System.out.println(object);
		return object;
	}

	/**Metodo che restituisce la descrizione dei post da cui si ricava il nome della città
	 *
	 */
	@Override
	/*public String getMessage_Post() {
		try {
			JSONObject object = getPost_User();
			JSONObject object2 = object.getJSONObject("posts");
			JSONArray array = object2.getJSONArray("data");
			for (int i = 0; i < array.length(); i++) { // si usa un for normale perchè get() richiede il parametro
				message = array.getJSONObject(i).getString("message");
				System.out.println(message);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}*/

	/**Questo metodo salva su ArrayList tutti i post
	 * 
	 * @throws JSONException
	 */
	public void allPosts() throws JSONException {
		JSONObject object = getPost_User();
		JSONObject object2 = object.getJSONObject("posts");
		JSONArray data = object2.getJSONArray("data");
		for (int i = 0; i < data.length(); i++) {
			String createdTime = data.getJSONObject(i).getString("created_time");
			String message = data.getJSONObject(i).getString("message");
			posts.add(new Post(createdTime, message));
		}
	}
	
	/**Metodo che restituisce in JSONArray i post che contengono il parametro city (ignorando il letter case)
	 * 
	 * @param city Città da cercare
	 * @return JSONArray contenente tutti i post in cui è contenuta la città
	 */
	public JSONArray getPostsfromCity(String city) throws Exception { // restituisce JSONArray normale NON JSON Array simple

		// TODO Due eccezioni fare: uno se non è presente city e un'altra per i
		// JSONObject sotto.
		
		ServiceImpl serviceImpl = new ServiceImpl();
		JSONObject object = serviceImpl.getPost_User();
		JSONObject object2 = object.getJSONObject("posts");
		JSONArray array = object2.getJSONArray("data");
		JSONArray ArrayPostsfromCity = new JSONArray();

		for (int i = 0; i < array.length(); i++) {
			String message = array.getJSONObject(i).getString("message").toLowerCase();
			if (message.contains(city.toLowerCase())) {
				ArrayPostsfromCity.put(array.getJSONObject(i));
			}
		}
		System.out.println(ArrayPostsfromCity);
		return ArrayPostsfromCity;
	}

	/** Questo metodo mappa ogni post ad ogni location, salvandolo su HashMap
	 * 
	 * @throws JSONException
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void PostLocationMap() throws JSONException, FileNotFoundException, IOException, ParseException {
		allPosts();
		FileManagement database = new FileManagement();
		database.getFile();
		JSONObject object = getPost_User();
		JSONObject object2 = object.getJSONObject("posts");
		JSONArray data = object2.getJSONArray("data");
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
		
		for (Post name: map.keySet()) {
		    String key = name.toString();
		    String value = map.get(name).toString();
		    System.out.println(key + " " + value);
		}
	}
	
	//public JSONArray getPostsFromProvince(String city){
		
		
		
	//}
}
