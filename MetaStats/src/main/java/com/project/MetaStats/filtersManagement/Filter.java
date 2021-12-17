package com.project.MetaStats.filtersManagement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import com.project.MetaStats.model.Location;
import com.project.MetaStats.model.Post;

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
	 */
	public abstract String filter() throws FileNotFoundException, JSONException, IOException, ParseException;
	
}
