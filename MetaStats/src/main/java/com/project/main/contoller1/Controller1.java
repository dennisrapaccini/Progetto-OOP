package com.project.main.contoller1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Controller1 {
	@RequestMapping("/") //Indica a Spring che il metodo risponda ad un determinato path ("/")
	@ResponseBody //Indica che tutto il contenuto restituito da questo metodo farà parte della pagina che verrà mostrata all'utente
	public String vaffanculo() {
		return "Vaffanculo a tutti";
	}
	
	@RequestMapping("/porco") 
	@ResponseBody 
	public String nuovaPagina() {
		return "Porco dio";
	}
}
