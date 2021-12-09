package com.project.main.service;

import org.springframework.web.client.RestTemplate;

/**Classe che fa l'override dei metodi definiti in Service
 * @author Cheikh
 * @author Dennis
 */
public class ServiceImpl implements Service{
	
	/**Attributi definiti per implementare i metodi
	 */
	private final String token = "EAANA9YBtvWIBAHZBZCr8Smec7zsSUFF5cio8CcvtxtAhgh1bz0w1RkoZBZBOUJKeqshs5XFlbKNAGL1MvtefsgZBNAZAaiok2MNLWOv9IoZBoHHrPEe9JcZC40JOuFWgzjywZBmC5Mg6liLvNOdBf5x8McQSCyaj1ZCYHQ9wDtDAZBOFxRfsCZB8bQQX";
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
