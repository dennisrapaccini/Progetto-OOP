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

import com.project.MetaStats.exception.EmptyListException;
import com.project.MetaStats.exception.FileManagementException;
import com.project.MetaStats.exception.NonExistingLocationException;
import com.project.MetaStats.exception.WrongFieldException;
import com.project.MetaStats.exception.WrongParameterException;
import com.project.MetaStats.filtersManagement.FilterCity;
import com.project.MetaStats.filtersManagement.FilterProvince;
import com.project.MetaStats.filtersManagement.FilterRegion;
import com.project.MetaStats.service.ServiceImpl;

/**
 * Classe che gestisce le rotte
 * 
 * @author Cheikh Cisse
 * @author Dennis Rapaccini
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
	 * Rotta di tipo GET che mostra i post filtrati per location
	 * 
	 * @param city città da filtrare
	 * @return ResponseEntity
	 * @throws NonExistingLocationException
	 * @throws FileManagementException
	 */
	@GetMapping(value = "/posts/city")
	public ResponseEntity<Object> getPostsFromLocation(@RequestParam(required = true) List<String> city)
			throws Exception{
		try {
			return new ResponseEntity<>(service.getPostsFromParameters("city", city).toString(), HttpStatus.OK);
		} 
		catch (NonExistingLocationException e) {
			return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		} 
		catch (FileManagementException e) {
			return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		} 
		
	}

	/**
	 * Rotta di tipo GET che mostra i post filtrati dalle province relative alle città postate dall'utente
	 * 
	 * @param province provincia da filtrare
	 * @return ResponseEntity
	 * @throws NonExistingLocationException
	 * @throws FileManagementException
	 */
	@GetMapping(value = "/posts/province")
	public ResponseEntity<Object> getPostsFromProvince(@RequestParam(required = true) List<String> province)
			throws Exception{
		try {
			return new ResponseEntity<>(service.getPostsFromParameters("province", province).toString(), HttpStatus.OK);
		} 
		catch (NonExistingLocationException e) {
			return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		}
		catch (FileManagementException e) {
			return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		} 
		
	}

	/**
	 * Rotta di tipo GET che mostra i post filtrati dalle regioni relative alle città postate dall'utente
	 * 
	 * @param region regione da filtrare
	 * @return ResponseEntity
	 * @throws Exception
	 */
	@GetMapping(value = "/posts/region")
	public ResponseEntity<Object> getPostsFromRegion(@RequestParam(required = true) List<String> region)
			throws Exception {
		try {
			return new ResponseEntity<>(service.getPostsFromParameters("region", region).toString(), HttpStatus.OK);
		} 
		catch (NonExistingLocationException e) {
			return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		}
		catch (FileManagementException e) {
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
	 * @throws EmptyListException 
	 * @throws FileManagementException
	 */
	@GetMapping(value = "posts/stats/ranking")
	public ResponseEntity<Object> ranking(@RequestParam (required = true) String type,
										  @RequestParam (required = false) String initialDate,
										  @RequestParam (required = false) String finalDate) throws Exception {
		try {
			return new ResponseEntity<>(service.ranking(type,initialDate,finalDate).toString(), HttpStatus.OK);
		}
		catch(WrongFieldException e) {
			return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		}
		catch(WrongParameterException e) {
			return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		}
		catch(FileManagementException e) {
			return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		}
		catch(EmptyListException e) {
			return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Rotta di tipo GET che mostra tutte le location in cui l'utente ha postato.
	 * 
	 * @param type tipo di location: city, province o region
	 * @return ResponseEntity
	 * @throws FileNotFoundException
	 * @throws NonExistingLocationException
	 * @throws IOException
	 * @throws ParseException
	 * @throws JSONException
	 * @throws FileManagementException 
	 * @throws WrongFieldException
	 */
	@GetMapping(value = "posts/location")
	public ResponseEntity<Object> prova(@RequestParam(required = true) String type) throws Exception{
		try {
			return new ResponseEntity<>(service.getLocationFromPosts(type).toString(), HttpStatus.OK);
		}
		catch(FileManagementException e) {
			return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		}
		catch(WrongFieldException e) {
			return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		}
	}
}
