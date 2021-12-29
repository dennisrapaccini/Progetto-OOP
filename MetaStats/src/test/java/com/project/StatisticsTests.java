package com.project;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe che testa un metodo di Statistics
 * 
 * @author Cheikh Cisse
 * @author Dennis Rapaccini
 */
class StatisticsTests {
	
	HashMap<String, Integer> hm;
	
	/**
	 * Ininizializza gli oggetti necessari al test
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		hm = new HashMap<String, Integer>();
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
	void test() {
		fail("Not yet implemented");
	}

}
