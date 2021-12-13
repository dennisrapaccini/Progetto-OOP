package com.project.MetaStats.filtersManagement;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

public class FilterProvince extends Filter{
	
	FileManagement database = new FileManagement();
	private String province;
	
	/**
	 * Metodo che controlla se la provincia (in sigla) da parametro è presente nel database (ignorando il letter case)
	 * 
	 * 
	 * @param city La provincia da controllare
	 * @return true se la provincia è presente, false altrimenti
	 * @throws JSONException
	 * @throws ParseException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public boolean isProvince(String province) throws FileNotFoundException, IOException, ParseException, JSONException {
		boolean isProvince = false;
		database.getFile();
		for (int i = 0; i < database.getCityList().size(); i++) {
			if (database.getCityList().get(i).getProvince().equalsIgnoreCase(province)) { 
				isProvince = true;
			}
		}
		return isProvince;
	}
	
	@Override
	public String filter() {
		// TODO Auto-generated method stub
		return null;
	}

}
