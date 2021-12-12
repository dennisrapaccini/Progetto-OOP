package com.project.MetaStats.contoller;

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
	public ResponseEntity<Object> getPost(@RequestParam(required = false) String city) throws Exception {

		if (city == null) //
			return new ResponseEntity<>(service.getPost_User().toString(), HttpStatus.OK);
		else {
			if (filter.isCity(city))
				return new ResponseEntity<>(filter.getPostsfromCity(city).toString(), HttpStatus.OK);
			else
				throw new Exception(); // aggiungere eccezione personalizzata (non in questo modo, guardare su codice
										// di Federica)
		}
	}
}
