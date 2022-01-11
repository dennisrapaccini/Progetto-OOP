package com.project.MetaStats.filtersManagement;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import com.project.MetaStats.exception.FileManagementException;

/**
 * Classe astratta che definisce i filtri utilizzati
 * 
 * @author Cheikh Cisse
 * @author Dennis Rapaccini
 *
 */
public abstract class Filter {
	
	/**
	 * Costruttore vuoto della superclasse filtro
	 */
	public Filter() {
		super();
	}
	
	/**
	 * Metodo astratto che verrà implementato  dalle sottoclassi
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
