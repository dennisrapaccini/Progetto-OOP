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
	 * Rotta di tipo GET che filtra i post per provincia. E' possibile immettere più di una provincia.
	 * 
	 * @param province in sigla (es. AN)
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
	 * Rotta di tipo GET che filtra i post per regione. E' possibile immettere più di una regione.
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
	 * Rotta di tipo GET che mostra la classifica per numero di occorrenze di città, province o regioni.
	 * E' possibile limitare ulteriormente la classifica per intervallo temporale inserendo i parametri initialDate e finalDate.
	 * 
	 * @param type Tipo di location. Sono ammessi solo "city", "province" e "region"
	 * @param initialDate (Opzionale) Data iniziale per il filtraggio in formato "dd-MM-yyyy". Se non presente è impostata di default a "04-02-2021", ossia la data di creazione di Facebook
	 * @param finalDate (Opzionale) Data finale per il filtraggio in formato "dd-MM-yyyy". Se non presente è impostata di default alla data attuale
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
		catch (EmptyListException e) {
			return new ResponseEntity<>(e.getError(), HttpStatus.BAD_REQUEST);
		}
	}
	//FilterCity,Region,Province NON USATI!!!!!! FARE ROTTE!!!!!
	
	/*Bisogna fare le cose uniformate, quindi o mettiamo per ogni tipo (city, province o region) una rotta (vedi posts/city ecc...) 
	 oppure prendere type come parametro.
	 Nel primo caso in totale verebbero 10 rotte e bisognerebbe cambiare qualcosina, nel secondo caso verrebbero 4 rotte.
	 */
	
}
