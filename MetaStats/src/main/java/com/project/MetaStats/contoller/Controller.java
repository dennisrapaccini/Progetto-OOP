package com.project.MetaStats.contoller;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.MetaStats.exception.NonExistingLocationException;
import com.project.MetaStats.exception.WrongFieldException;
import com.project.MetaStats.exception.WrongParameterException;
import com.project.MetaStats.filtersManagement.FilterCity;
import com.project.MetaStats.filtersManagement.FilterProvince;
import com.project.MetaStats.filtersManagement.FilterRegion;
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
	FilterProvince filter2 = new FilterProvince();
	FilterRegion filter3 = new FilterRegion();

	/**
	 * Rotta di tipo GET che mostra tutti i post relativi alla città richiesta se
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

	/**
	 * Rotta di tipo GET che mostra i post che sono filtrati tramite location
	 * 
	 * @param city città di cui si vogliono visualizzare i post
	 * @return ResponseEntity
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException
	 * @throws Exception
	 */
	@GetMapping(value = "/posts/city")
	public ResponseEntity<Object> getPostsFromLocation(@RequestParam(required = true) List<String> city)
			throws FileNotFoundException, IOException, ParseException, JSONException, Exception {
		try {
			return new ResponseEntity<>(service.getPostsFromParameters("city", city).toString(), HttpStatus.OK);
		} catch (NonExistingLocationException e) {
			return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Rotta di tipo GET che mostra i post filtrati dalle province visitate
	 * dall'utente
	 * 
	 * @param province
	 * @return ResponseEntity
	 * @throws Exception
	 */
	@GetMapping(value = "/posts/province")
	public ResponseEntity<Object> getPostsFromProvince(@RequestParam(required = true) List<String> province)
			throws Exception, FileNotFoundException, JSONException, IOException, ParseException {
		try {
			return new ResponseEntity<>(service.getPostsFromParameters("province", province).toString(), HttpStatus.OK);
		} catch (NonExistingLocationException e) {
			return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Rotta di tipo GET che mostra i post filtrati secondo le regioni visitate
	 * dall'utente
	 * 
	 * @param region
	 * @return ResponseEntity
	 * @throws Exception
	 */
	@GetMapping(value = "/posts/region")
	public ResponseEntity<Object> getPostsFromRegion(@RequestParam(required = true) List<String> region)
			throws Exception {
		try {
			return new ResponseEntity<>(service.getPostsFromParameters("region", region).toString(), HttpStatus.OK);
		} catch (NonExistingLocationException e) {
			return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		}
	}

	
	/**
	 * Rotta di tipo GET che mostra il ranking delle città, province o regioni più
	 * visitate dall'utente a seconda del parametro immesso dall'utente
	 * 
	 * @param type tipo di ranking che si vuole fare
	 * @return ResponseEntity
	 * @throws FileNotFoundException
	 * @throws JSONException
	 * @throws IOException
	 * @throws ParseException
	 * @throws WrongParameterException
	 */
	@GetMapping(value = "posts/stats/ranking")
	public ResponseEntity<Object> ranking(@RequestParam (required = true) String type,
										  @RequestParam (required = false) String initialDate,
										  @RequestParam (required = false) String finalDate) throws FileNotFoundException, JSONException, IOException, ParseException, WrongParameterException {
		try {
			return new ResponseEntity<>(service.ranking(type,initialDate,finalDate).toString(), HttpStatus.OK);
		}
		catch(WrongFieldException e) {
			return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		}
		catch(WrongParameterException e) {
			return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value = "posts/location")
	public ResponseEntity<Object> prova(@RequestParam(required = true) String type) throws FileNotFoundException, NonExistingLocationException, IOException, ParseException, JSONException{
		return new ResponseEntity<>(service.getPostsFromCity(type).toString(), HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
}
