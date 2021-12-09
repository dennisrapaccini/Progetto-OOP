package com.project.MetaStats.service;

import org.springframework.web.client.RestTemplate;

/**Classe che fa l'override dei metodi definiti in Service
 * @author Cheikh
 * @author Dennis
 */
public class ServiceImpl implements Service{
	
	/**Attributi definiti per implementare i metodi
	 */
	private final String token = "EAANA9YBtvWIBAKF6vqQ8pViegZCzFj3FveZBaBwwayBMqrlO0ZAsvds8iDN0A9SipAi1oVm5Rx5Hzqx8MvKXfs9cP8JNIDgBgIb2qZCehPkpHo1rOjUEkuOz5dhaWZABOL6s9dBZCzRNOZAHQj06O64N8xtNYZBDPU3U5tMnpKzHfkywTyScP2ef";
	private String url = "https://graph.facebook.com/me?fields=posts&access_token="+token;
	
	/**Oggetto RestTemplate che gestisce le richieste http
	 */
	RestTemplate rt = new RestTemplate();
	
	/**Metodo che restituisce il contenuto fornito dall'API in formato stringa
	 */
	@Override
	public String method() {
		String ris = rt.getForObject(url, String.class);
		System.out.println(ris);
		return ris;	
	}

}
