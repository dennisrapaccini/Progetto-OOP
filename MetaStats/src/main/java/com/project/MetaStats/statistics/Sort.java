package com.project.MetaStats.statistics;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Classe che implementa il metodo sort per mettere in ordine un HashMap
 * 
 * @author Cheikh Cisse
 * @author Dennis Rapaccini
 */
public class Sort {
	
	/**
	 * Metodo che ordina un HashMap con key String e value Integer in ordine di value decrescenti
	 * 
	 * @param hm HashMap con key String e value Integer
	 * @return HashMap ordinato
	 */
	public static HashMap<String, Integer> hmSort(HashMap<String, Integer> hm){
		List<Map.Entry<String, Integer>> list = 
				new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
				public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
			return (o1.getValue()).compareTo(o2.getValue());
		}
	});
		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		for(int i = list.size()-1; i >= 0; i--) {
			temp.put(list.get(i).getKey(), list.get(i).getValue());
		}
		return temp;
    }
	
}