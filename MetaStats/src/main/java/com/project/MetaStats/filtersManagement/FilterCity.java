package com.project.MetaStats.filtersManagement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import com.project.MetaStats.model.Location;
import com.project.MetaStats.model.Post;
import com.project.MetaStats.service.ServiceImpl;

public class FilterCity extends Filter {
	
	/**Attributi relativi alla classe FilterCity
	 */
	private String city;
	FileManagement database = new FileManagement();
	
	/**Costruttore della sottoclasse FilterCity estensione della superclasse Filter
	 * @param city
	 */
	public FilterCity() {
		super();
	}

	/**Metodo che restituisce l'attributo city(getter)
	 * @return city
	 */
	public String getCity() {
		return city;
	}
	
	/**Metodo che setta l'attributo city(setter)
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Metodo che controlla se la città da parametro è presente nel database (ignorando il letter case)
	 * 
	 * Se il nome della città contiene più di una parola, queste vanno inserite tutte
	 * 
	 * @param city La città da controllare
	 * @return true se la città è presente, false altrimenti
	 * @throws JSONException
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public boolean isCity(String city) throws FileNotFoundException, IOException, ParseException, JSONException {
		// Bisogna scegliere se contains o equals: se si
		// sceglie contains, il metodo rileva anche singole
		// lettere come città. Se si sceglie il secondo, se
		// una città ha due parole (es. Civitanova Marche) e
		// l'utente scrive solo Civitanova, non la prende
		// SOLUZIONE: Si potrebbe controllare solo una parola della stringa in ingresso
		boolean isCity = false;
		database.getFile();
		for (int i = 0; i < database.getCityList().size(); i++) {
			if (database.getCityList().get(i).getCity().toLowerCase().equals(city.toLowerCase())) { 
				isCity = true;
			}
		}
		return isCity;
	}

	@Override
	public String filter() throws FileNotFoundException, JSONException, IOException, ParseException {
		HashMap<Post, Location> hm;
		ServiceImpl service = new ServiceImpl();
		hm = service.PostLocationMapping();
		ArrayList<Location> loc = new ArrayList<Location>(hm.values());
		for(int i = 1; i < loc.size(); i++) {
			city += loc.get(i).getCity() + " ";
		}
		System.out.println(city);
		return city;
	}
	
	

}