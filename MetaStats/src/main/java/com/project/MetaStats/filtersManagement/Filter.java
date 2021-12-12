package com.project.MetaStats.filtersManagement;

import java.util.*;

public abstract class Filter {
	
	/**Attributi della classe Filter
	 */
	private ArrayList<String> filtered = new ArrayList<String>();
	
	/**Costruttore della superclasse filtro
	 * @param cityList è la lista delle città che vogliamo filtrare
	 * @param filtered è la lista della cosa che abbiamo filtrato
	 */
	public Filter() {
		super();
	}
	
	/**Metodo che restituisce un arrayList di ciò che è stato filtrato
	 * @return filtered
	 */
	public ArrayList<String> getFiltered() {
		return filtered;
	}
	
	/**Metodo che setta l'arrayList di ciò che è stato filtrato
	 * @param filtered
	 */
	public void setFiltered(ArrayList<String> filtered) {
		this.filtered = filtered;
	}
	
	public abstract String filter();
	
}
