package com.project.MetaStats.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.MetaStats.service.ServiceImpl;

/**Classe che gestisce le rotte che si possono fare
 * @author Cheikh
 * @author Dennis
 */
@RestController
public class Controller {
	 //Indica a Spring che il metodo risponda ad un determinato path ("/")
	 /*Indica che tutto il contenuto restituito da questo metodo farà parte della pagina
	   che verrà mostrata all'utente*/
	ServiceImpl service = new ServiceImpl();
	
	@GetMapping(value = "/post")//prova
	public ResponseEntity<Object> prova(){
		return new ResponseEntity<>(service.method().toString(), HttpStatus.OK);
	}

}
