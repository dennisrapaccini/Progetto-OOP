package com.project;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.project.MetaStats.filtersManagement.FileManagement;

class FileManagementTests {
	
	FileManagement file;

	@BeforeEach
	void setUp() throws Exception {
		file = new FileManagement();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Test assert ParseException")
	void ParseExceptionTest() {
		ParseException parse = assertThrows(ParseException.class, () -> {
			file.getFile();
		});
		assertEquals("ERRORE! Parsing errato! Controlla il contenuto del file", parse.getMessage());
	}
}
