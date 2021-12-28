package com.project;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.project.MetaStats.exception.FileManagementException;
import com.project.MetaStats.exception.NonExistingLocationException;
import com.project.MetaStats.service.ServiceImpl;

class ServiceImplTests {
	
	ServiceImpl service;
	JSONObject obj;
	JSONParser parser;
	
	@BeforeEach
	void setUp() throws Exception {
		service = new ServiceImpl();
		obj = new JSONObject();
		parser = new JSONParser();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Test getPostsFromCity")
	void getPostsFromCitytest() throws FileNotFoundException, NonExistingLocationException, IOException, ParseException, JSONException, FileManagementException {
		obj = (JSONObject) parser.parse("{\"Posts in firenze\":[{\"created_time\":\"2021-12-25T12:46:40+0000\",\"id\":\"107864155075941_119047300624293\",\"message\":\"Buon Natale a tutti i miei amici di Facebook, oggi pranzo con i lontani parenti di Firenze\"}]}");
		assertEquals(service.getPostsFromCity("firenze").toString(), obj.toString());
	}
}
