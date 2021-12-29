package com.project;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.project.MetaStats.filtersManagement.FileManagement;

/**
 * Classe che testa un metodo di FileManagement
 * 
 * @author Cheikh Cisse
 * @author Dennis Rapaccini
 */
class FileManagementTests {
	
	FileManagement file;
	
	/**
	 * Ininizializza gli oggetti necessari al test
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		file = new FileManagement();
	}
	
	/**
     * Serve per distruggere ciò che è stato inizializzato dal metodo setUp.
     * 
     * @throws java.lang.Exception
     */
	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
	 * Questo metodo verifica l'eccezione ParseException e la sua gestione
	 */
	@Test
	@DisplayName("Test assert ParseException")
	void ParseExceptionTest() {
		ParseException parse = assertThrows(ParseException.class, () -> {
			file.getFile();
		});
		assertEquals("ERRORE! Parsing errato! Controlla il contenuto del file", parse.getMessage());
	}
	
}