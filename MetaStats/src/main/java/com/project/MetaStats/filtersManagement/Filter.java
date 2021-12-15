package com.project.MetaStats.filtersManagement;

public abstract class Filter {
	
	/**Costruttore della superclasse filtro
	 * @param cityList è la lista delle città che vogliamo filtrare
	 * @param filtered è la lista della cosa che abbiamo filtrato
	 */
	public Filter() {
		super();
	}
	
	public abstract String filter();
	
}
