package com.project;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.MetaStats.exception.EmptyListException;
import com.project.MetaStats.exception.FileManagementException;
import com.project.MetaStats.exception.WrongFieldException;
import com.project.MetaStats.exception.WrongParameterException;
import com.project.MetaStats.statistics.Statistics;

/**
 * Classe che testa un metodo di Statistics
 * 
 * @author Cheikh Cisse
 * @author Dennis Rapaccini
 */
class StatisticsTests {
	
	Statistics stats;
	
	/**
	 * Ininizializza gli oggetti necessari al test
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		stats = new Statistics();
	}
	
	/**
     * Serve per distruggere ciò che è stato inizializzato dal metodo setUp.
     * 
     * @throws java.lang.Exception
     */
	@AfterEach
	void tearDown() throws Exception {
	}
	
	
	@Test
	void test() throws FileNotFoundException, JSONException, IOException, ParseException, WrongParameterException, WrongFieldException, EmptyListException, FileManagementException {
		String rank = "{Marche=11, Umbria=3, Sardegna=3, Basilicata=2, Piemonte=1, Lazio=1, Molise=1, Lombardia=1, Toscana=1}";
		assertEquals(stats.ranking("region", null, null).toString(), rank);
	}

}
