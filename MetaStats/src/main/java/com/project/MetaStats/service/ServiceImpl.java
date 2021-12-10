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
	
	/**token di accesso alle API fornito all'utente da Facebook
	 */
	private String token = "EAANA9YBtvWIBAJVOBFZAWLiYDhrWXNafdljZAchkhEmp5IZB3xJZBSfOty8eZBCupZB6OGoDySLRa79PxNZBEH5cyQBVLXEXK3Ql7V3tO5ubYuek3SVhqcD7sVicW6Hz0uir2JJik4ew4cjvmu6deZCFTnXop9sMqgE13U0r9OmV7qXzTAfaJ7RzQDlZAZBkPzsRtwhcZAe2pr6XoBgQV1bmmDvkTGYGRYnY7PmdA9QqkC0QyREcHmoFx7g1pPoTn0XnCMZD";
	
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
