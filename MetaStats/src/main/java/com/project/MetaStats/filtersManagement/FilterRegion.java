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

public class FilterRegion extends Filter{
	
	FileManagement database = new FileManagement();
	private String region;
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}


	/**
	 * Metodo che controlla se la regione da parametro è presente nel database (ignorando il letter case)
	 * 
	 * @param city La regione da controllare
	 * @return true se la regione è presente, false altrimenti
	 * @throws JSONException
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public boolean isRegion(String region) throws FileNotFoundException, IOException, ParseException, JSONException {
		// Stesso problema di isCity, stavolta equals forse è la soluzione migliore (anche se ad esempio c'è Trentino-Alto Adige/Südtirol)
		// conviene controllare la prima parola in equals e basta. DA FARE.
		boolean isRegion = false;
		database.getFile();
		for (int i = 0; i < database.getCityList().size(); i++) {//Molta ridondanza nelle regioni. Possibile ottimizzazione
			if (database.getCityList().get(i).getRegion().equalsIgnoreCase(region)) { 
				isRegion = true;
			}
		}
		return isRegion;
	}
	
	
	@Override
	public String filter() throws FileNotFoundException, JSONException, IOException, ParseException {
		HashMap<Post, Location> hm;
		ServiceImpl service = new ServiceImpl();
		hm = service.PostLocationMapping();
		ArrayList<Location> loc = new ArrayList<Location>(hm.values());
		for(int i = 0; i < loc.size(); i++) {
			region += loc.get(i).getProvince() + " ";
		}
		System.out.println(region);
		return region;
	}

}
