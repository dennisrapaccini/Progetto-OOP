package com.project.MetaStats.filtersManagement;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import com.project.MetaStats.exception.FileManagementException;
import com.project.MetaStats.model.Location;
import com.project.MetaStats.model.Post;
import com.project.MetaStats.service.ServiceImpl;

/**
 * Sottoclasse della classe Filter che implementa il metodo astratto della Superclasse
 * 
 * @author Cheikh Cisse
 * @author Dennis Rapaccini
 *
 */
public class FilterRegion extends Filter{
	
	FileManagement database = new FileManagement();
	private String region = "";
	
	/**
	 * Metodo che restituisce l'attributo region(getter)
	 * 
	 * @return region
	 */
	public String getRegion() {
		return region;
	}
	
	/**
	 * Metodo che setta l'attributo province(setter)
	 * 
	 * @param region
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * Metodo che controlla se la regione da parametro è presente nel database (ignorando il letter case)
	 * 
	 * @param region La regione da controllare
	 * @return true se la regione è presente, false altrimenti
	 * @throws JSONException
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws FileManagementException 
	 */
	public boolean isRegion(String region) throws FileNotFoundException, IOException, ParseException, JSONException, FileManagementException {
		boolean isRegion = false;
		database.getFile();
		for (int i = 0; i < database.getCityList().size(); i++) {
			if (database.getCityList().get(i).getRegion().equalsIgnoreCase(region)) { 
					isRegion = true;
			}
		}
		return isRegion;
	}
	
	/**
	 * Metodo che fa l'override del metodo astratto filter e restituisce tette le regioni in cui l' utente è stato
	 * 
	 * @return region regioni filtrate
	 * @throws FileManagementException 
	 */
	@Override
	public ArrayList<String> filter() throws FileNotFoundException, JSONException, IOException, ParseException, FileManagementException {
		HashMap<Post, Location> hm;
		ServiceImpl service = new ServiceImpl();
		hm = service.PostLocationMapping();
		ArrayList<Location> loc = new ArrayList<Location>(hm.values());
		ArrayList<String> aL = new ArrayList<String>();
		ArrayList<String> regionList = new ArrayList<String>();
		for(int i = 1; i < loc.size(); i++) {
			region += loc.get(i).getRegion() + ",";
			aL = new ArrayList<String>(Arrays.asList(region.split(",")));
		}
		for(String str : aL) {
			if(!regionList.contains(str)) {
				regionList.add(str);
			}
		}
		return regionList;
	}

}
