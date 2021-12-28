package com.project.MetaStats.filtersManagement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import com.project.MetaStats.exception.FileManagementException;

public abstract class Filter {
	
	/**Costruttore della superclasse filtro
	 * @param cityList è la lista delle città che vogliamo filtrare
	 * @param filtered è la lista della cosa che abbiamo filtrato
	 */
	public Filter() {
		super();
	}
	/**Metodo astratto che verrà implementato  dalle sottoclassi
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws JSONException
	 * @throws IOException
	 * @throws ParseException
	 * @throws FileManagementException 
	 */
	public abstract ArrayList<String> filter() throws FileNotFoundException, JSONException, IOException, ParseException, FileManagementException;
	
}
