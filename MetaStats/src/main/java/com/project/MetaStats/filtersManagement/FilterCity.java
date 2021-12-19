package com.project.MetaStats.filtersManagement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import com.project.MetaStats.model.Location;
import com.project.MetaStats.model.Post;
import com.project.MetaStats.service.ServiceImpl;

public class FilterCity extends Filter {
	
	/**Attributi relativi alla classe FilterCity
	 */
	private String city = "";
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
		boolean isCity = false;
		database.getFile();
		for (int i = 0; i < database.getCityList().size(); i++) {
			if (database.getCityList().get(i).getCity().toLowerCase().equals(city.toLowerCase())) { 
				isCity = true;
			}
		}
		return isCity;
	}
	
	/**Metodo che fa l'override del metodo astratto filter
	 * 
	 * @return city stringa con le città filtrate
	 */
	@Override
	public ArrayList<String> filter() throws FileNotFoundException, JSONException, IOException, ParseException {
		HashMap<Post, Location> hm;
		ServiceImpl service = new ServiceImpl();
		hm = service.PostLocationMapping();
		ArrayList<Location> loc = new ArrayList<Location>(hm.values());
        ArrayList<String> aL = new ArrayList<String>();
        ArrayList<String> cityList = new ArrayList<String>();
		for(int i = 1; i < loc.size(); i++) {
			city += loc.get(i).getCity() + ",";
			aL = new ArrayList<String>(Arrays.asList(city.split(",")));
		}
		for(String str : aL) {
			if(!cityList.contains(str)) {
				cityList.add(str);
			}
		}
		System.out.println(cityList);
		return cityList;
	}
}