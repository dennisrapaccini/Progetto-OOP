package com.project.MetaStats.statistics;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import com.project.MetaStats.model.*;
import com.project.MetaStats.service.*;

/**Classe che implementa i metodi per effettuare statistiche sui post
 * 
 * @author Cheikh
 * @author Dennis
 *
 */
public class Statistics {
	
	/**Metodo che ritorna il ranking delle città, province o regioni visitate dall'utente
	 * 
	 * @return HashMap che ha al suo interno un mapping delle città, 
	 * province o regioni più visitate dall'utente.
	 * @throws FileNotFoundException
	 * @throws JSONException
	 * @throws IOException
	 * @throws ParseException
	 */
	public HashMap<String, Integer> ranking(String type) throws FileNotFoundException, JSONException, IOException, ParseException {
		HashMap<String, Integer> occurences = new HashMap<String, Integer>();
		HashMap<Post, Location> hm = new HashMap<Post, Location>();
		ServiceImpl service = new ServiceImpl();
		hm = service.PostLocationMapping();
		ArrayList<Location> loc = new ArrayList<Location>(hm.values());
		//try catch con eccezione per il type non riconosciuto ovvero diverso da city province o region
		switch(type) {
		case "city" :
		for(int i = 0; i < loc.size(); i++) {
			int counter = 0;
			for(int j = 0; j < loc.size(); j++) {
				if (loc.get(i).getCity().equals(loc.get(j).getCity())) {
					counter++;
				}
			}
			occurences.put(loc.get(i).getCity(), counter);
		}
		break;
		case "province" : 
			for(int i = 0; i < loc.size(); i++) {
				int counter = 0;
				for(int j = 0; j < loc.size(); j++) {
					if (loc.get(i).getProvince().equals(loc.get(j).getProvince())) {
						counter++;
					}
				}
				occurences.put(loc.get(i).getProvince(), counter);
			}
			break;
		case "region" :
			for(int i = 0; i < loc.size(); i++) {
				int counter = 0;
				for(int j = 0; j < loc.size(); j++) {
					if (loc.get(i).getRegion().equals(loc.get(j).getRegion())) {
						counter++;
					}
				}
				occurences.put(loc.get(i).getRegion(), counter);
			}
			break;
		}
		//System.out.println(Sort.hmSort(occurences));
	return Sort.hmSort(occurences);
	}
	
	public HashMap<String, Integer> rankingByPeriod(String type, String initialDate, String finalDate) throws FileNotFoundException, JSONException, IOException, ParseException {
		HashMap<String, Integer> rank = new HashMap<String, Integer>();
		HashMap<Post, Location> hm = new HashMap<Post, Location>();
		ServiceImpl service = new ServiceImpl();
		hm = service.PostLocationMapping();
		ArrayList<Location> loc = new ArrayList<Location>(hm.values());
		
		ArrayList<Location> date = new ArrayList<Location>();
		for(int i = 0; i < loc.size(); i++) {
			int counter = 0;
			for(int j = 0; j < loc.size(); j++) {
				if (loc.get(i).getCity().equals(loc.get(j).getCity())) {
					counter++;
				}
			}
			rank.put(loc.get(i).getCity(), counter);
		}
		
		
		return rank;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
