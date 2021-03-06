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
public class FilterProvince extends Filter{
	
	FileManagement database = new FileManagement();
	private String province = "";
	
	/**
	 * Metodo che restituisce l'attributo province(getter)
	 * 
	 * @return province
	 */
	public String getProvince() {
		return province;
	}
	
	/**
	 * Metodo che setta l'attributo province(setter)
	 * 
	 * @param province
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * Metodo che controlla se la provincia (in sigla) da parametro è presente nel database (ignorando il letter case)
	 * 
	 * @param province La provincia da controllare
	 * @return true se la provincia è presente, false altrimenti
	 * @throws JSONException
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws FileManagementException 
	 */
	public boolean isProvince(String province) throws FileNotFoundException, IOException, ParseException, JSONException, FileManagementException {
		boolean isProvince = false;
		database.getFile();
		for (int i = 0; i < database.getCityList().size(); i++) {
			if (database.getCityList().get(i).getProvince().equalsIgnoreCase(province)) { 
					isProvince = true;
			}
		}
		return isProvince;
	}
	
	/**
	 * Metodo che fa l'override della classe astratta filter e restituisce tutte le province in cui l' utente è stato
	 * 
	 * @return province province filtrate
	 * @throws FileManagementException 
	 */
	@Override
	public ArrayList<String> filter() throws FileNotFoundException, JSONException, IOException, ParseException, FileManagementException {
		HashMap<Post, Location> hm;
		ServiceImpl service = new ServiceImpl();
		hm = service.PostLocationMapping();
		ArrayList<Location> loc = new ArrayList<Location>(hm.values());
		ArrayList<String> aL = new ArrayList<String>();
		ArrayList<String> provinceList = new ArrayList<String>();
		for(int i = 1; i < loc.size(); i++) {
			province += loc.get(i).getProvince() + ",";
			aL = new ArrayList<String>(Arrays.asList(province.split(",")));
		}
		for(String str : aL) {
			if(!provinceList.contains(str)) {
				provinceList.add(str);
			}
		}
		return provinceList;
	}
	
}