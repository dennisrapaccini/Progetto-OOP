package com.project.MetaStats.filters;

import java.util.*;

public class Filter {
	
	/**Attributi della classe filter
	 */
	private ArrayList<String> cityList = new ArrayList<String>();
	private ArrayList<String> filtered = new ArrayList<String>();
	
	/**Costruttore della superclasse filtro
	 * @param cityList è la lista delle città che vogliamo filtrare
	 * @param filtered è la lista della cosa che abbiamo filtrato
	 */
	public Filter(ArrayList<String> cityList) {
		super();
		this.cityList = cityList;
	}

	public ArrayList<String> getCityList() {
		return cityList;
	}
	public void setCityList(ArrayList<String> cityList) {
		this.cityList = cityList;
	}
	public ArrayList<String> getFiltered() {
		return filtered;
	}
	public void setFiltered(ArrayList<String> filtered) {
		this.filtered = filtered;
	}
	
}
