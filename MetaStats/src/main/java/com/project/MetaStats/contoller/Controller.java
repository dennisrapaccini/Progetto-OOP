package com.project.MetaStats.contoller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.MetaStats.filtersManagement.FilterCity;
import com.project.MetaStats.service.ServiceImpl;

/**
 * Classe che gestisce le rotte che si possono fare
 * 
 * @author Cheikh
 * @author Dennis
 */
@RestController
public class Controller {

	ServiceImpl service = new ServiceImpl();
	FilterCity filter = new FilterCity();

	/**Rotta di tipo GET che mostra tutti i post relativi alla città richiesta se
	 * presente
	 * 
	 * @param city La città da cercare tra i post
	 * @return Tutti i post se il parametro non è presente, i post relativi al
	 *         parametro city altrimenti
	 * @throws Exception
	 */
	@GetMapping(value = "/posts")
	public ResponseEntity<Object> getPost() {
		return new ResponseEntity<>(service.getPost_User().toString(), HttpStatus.OK);
	}
	
	/**Rotta di tipo GET che mostra i post che sono filtrati tramite location
	 * 
	 * @param city
	 * @return ResponseEntity
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException
	 * @throws Exception
	 */
	@GetMapping (value = "/posts/location")
	public ResponseEntity<Object> getPostsFromLocation(@RequestParam(required = true) String city) throws FileNotFoundException, IOException, ParseException, JSONException, Exception{
	if (filter.isCity(city) && city!="")
		return new ResponseEntity<>(service.getPostsFromCity(city).toString(), HttpStatus.OK);
	else
		throw new Exception(); // aggiungere eccezione personalizzata (non in questo modo, guardare su codice
								// di Federica)
	}
	
	/**Rotta di tipo GET che mostra il ranking delle città, province o regioni più visitate dall'utente a 
	 * seconda del parametro immesso dall'utente
	 * 
	 * @param type
	 * @return ResponseEntity
	 * @throws FileNotFoundException
	 * @throws JSONException
	 * @throws IOException
	 * @throws ParseException
	 */
	@GetMapping (value = "/stats/ranking")
	public ResponseEntity<Object> ranking(@RequestParam(required = true) String type) throws FileNotFoundException, JSONException, IOException, ParseException{
		return new ResponseEntity<>(service.ranking(type).toString(), HttpStatus.OK);
	}
	
}
