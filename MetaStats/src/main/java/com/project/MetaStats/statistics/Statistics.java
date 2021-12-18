package com.project.MetaStats.statistics;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.springframework.lang.Nullable;

import com.project.MetaStats.exception.EmptyListException;
import com.project.MetaStats.exception.WrongFieldException;
import com.project.MetaStats.exception.WrongParameterException;
import com.project.MetaStats.model.*;
import com.project.MetaStats.service.*;

/**
 * Classe che implementa i metodi per effettuare statistiche sui post
 * 
 * @author Cheikh
 * @author Dennis
 *
 */
public class Statistics {

	/**
	 * Metodo che ritorna il ranking delle città, province o regioni visitate
	 * dall'utente, con la possibilità di filtrare la classifica limitatamente ad un intervallo temporale tra due date
	 * 
	 * 
	 * @return HashMap contenente un mapping delle città, province o regioni italiane più citate dall'utente. 
	 * @param  type Tipo di location. Sono ammessi solo "city", "province" e "region"
	 * @param  initialDate (Opzionale) Data iniziale per il filtraggio in formato "dd-MM-yyyy". Se non presente è impostata di default a "04-02-2021", ossia la data di creazione di Facebook
	 * @param  finalDate (Opzionale) Data finale per il filtraggio in formato "dd-MM-yyyy". Se non presente è impostata di default alla data attuale
	 * @throws FileNotFoundException
	 * @throws JSONException
	 * @throws IOException
	 * @throws ParseException
	 * @throws WrongFieldException 
	 * @throws EmptyListException 
	 */
	public HashMap<String, Integer> ranking(String type, @Nullable String initialDate, @Nullable String finalDate)
			throws FileNotFoundException, JSONException, IOException, ParseException, WrongParameterException, WrongFieldException, EmptyListException {
		HashMap<String, Integer> occurences = new HashMap<String, Integer>();
		HashMap<Post, Location> hm = new HashMap<Post, Location>();
		ServiceImpl service = new ServiceImpl();
		hm = service.PostLocationMapping();
		DateTimeFormatter userFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter FacebookFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
		if(initialDate == null) initialDate = "04-02-2004";  //Data creazione Facebook
		if(finalDate == null) finalDate = LocalDate.now().plusDays(1).format(userFormatter); //Data attuale + 1 (per via di isBefore())
		LocalDate date1;
		LocalDate date2;
		ArrayList<Location> loc = new ArrayList<Location>(hm.values());
		

		try {
			date1 = LocalDate.parse(initialDate, userFormatter).minusDays(1); //-1 per via del isAfter()
			date2 = LocalDate.parse(finalDate, userFormatter).plusDays(1); // +1 per via del isBefore()
		} catch (DateTimeParseException e) {
			throw new WrongParameterException("ERRORE! Inserisci data in formato dd-MM-yyyy", e);
		}
		boolean isEmpty= true;
		
		switch (type.toLowerCase()) {
		case "city":
			for (int i = 0; i < loc.size(); i++) { // scorre l'ArrayList di Location
				int counter = 0;
				int j = 0;
				for (Entry<Post, Location> set : hm.entrySet()) {
					LocalDate createdTime = (ZonedDateTime.parse(set.getKey().getCreatedTime(), FacebookFormatter))
							.toLocalDate();
					if (loc.get(i).getCity().equals(loc.get(j).getCity()) && createdTime.isAfter(date1) && createdTime.isBefore(date2)) {
						counter++;
						isEmpty=false;
					}
					j++;
				}
				occurences.put(loc.get(i).getCity(), counter);
			}

			break;

		case "province":
			for (int i = 0; i < loc.size(); i++) {
				int counter = 0;
				int j = 0;
				for (Entry<Post, Location> set : hm.entrySet()) {
					LocalDate createdTime = (ZonedDateTime.parse(set.getKey().getCreatedTime(), FacebookFormatter))
							.toLocalDate();
					if (loc.get(i).getProvince().equals(loc.get(j).getProvince()) && createdTime.isAfter(date1) && createdTime.isBefore(date2)) {
						counter++;
						isEmpty=false;
					}
					j++;
				}
				occurences.put(loc.get(i).getProvince(), counter);
			}

			break;

		case "region":
			for (int i = 0; i < loc.size(); i++) { 
				int counter = 0;
				int j = 0;
				for (Entry<Post, Location> set : hm.entrySet()) {
					LocalDate createdTime = (ZonedDateTime.parse(set.getKey().getCreatedTime(), FacebookFormatter))
							.toLocalDate();
					if (loc.get(i).getRegion().equals(loc.get(j).getRegion()) && createdTime.isAfter(date1) && createdTime.isBefore(date2)) {
						counter++;
						isEmpty=false;
					}
					j++;
				}
				occurences.put(loc.get(i).getRegion(), counter);
			}

			break;
		
		default : throw new WrongFieldException("ERRORE! Inserisci un tipo valido: solo city, province o region sono ammessi");
		}
		
		if (isEmpty) throw new EmptyListException("Non è presente alcun post per questo periodo, prova a cambiarlo o ad aumentarne l'intervallo temporale");
		
		System.out.println(Sort.hmSort(occurences));
		return Sort.hmSort(occurences);
	}

	
}
