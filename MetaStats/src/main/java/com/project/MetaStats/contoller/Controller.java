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
	
	ServiceImpl service = new ServiceImpl();
	
	/**Rotta di tipo get che mostra il risultato delle API riguardo i post dell'utente
	 * @return tutti i post dell'utente con le informazioni relative
	 */
	@GetMapping(value = "/post")
	public ResponseEntity<Object> getPost(){
		return new ResponseEntity<>(service.getPost_User().toString(), HttpStatus.OK);
	}

}
