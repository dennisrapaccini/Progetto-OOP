package com.project.MetaStats.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**Classe che fa l'override dei metodi definiti in Service
 * @author Cheikh
 * @author Dennis
 */
public class ServiceImpl implements Service{
	
	/**Token di accesso (a lungo termine) alle API fornito all'utente da Facebook.
	 * 
	 * L'access token è stato diviso per evitare invalidazioni da parte del controllo di Facebook su GitHub.
	 */
	private String preToken = "EAANA9YBtvWIBAGv7ZCzBbjKdVBLNXd1CipYksDZC";
	private String token = preToken+"Ogk488ah2zLZAsg1lRo8nWaPfS7XRZBGvZAtHYQ92p5Vt3ZC8dL4tjF0FqCJaG5iMzUw0hlYtHK0oN90mgYFiswcJ5B0VNBoZB2kw4cwOtes51wsxOYJw1wHZB7OtvKAHNWmp4DEWoQ98Q64";
	
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	/**Metodo che ritorna le API dell'utente riguardante i post in JSONObject
	 */
	@Override
	public JSONObject getPost_User() {
		JSONObject object = null;
		String url = "https://graph.facebook.com/me?fields=posts&access_token="+token;
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
	 */
	@Override
	public String getMessage_Post() {
		try {
			JSONObject object = getPost_User();
			JSONObject object2 = object.getJSONObject("posts");
			JSONArray array = object2.getJSONArray("data");
			for (int i = 0; i < array.length(); i++)
			{
			    message = array.getJSONObject(i).getString("message");
			    System.out.println(message);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
}
