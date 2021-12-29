package com.project;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.project.MetaStats.model.Location;
import com.project.MetaStats.model.Post;
import com.project.MetaStats.service.ServiceImpl;

/**
 * Classe che testa un metodo di ServiceImpl
 * 
 * @author Cheikh Cisse
 * @author Dennis Rapaccini
 */
class ServiceImplTests {
	
	ServiceImpl service;
	JSONObject obj1;
	JSONObject obj2;
	JSONArray arr;
	HashMap<Post,Location> map;
	
	/**
	 * Ininizializza gli oggetti necessari al test
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		service = new ServiceImpl();
		obj1 = new JSONObject();
		arr = new JSONArray();
		obj2 = new JSONObject();
		map = service.PostLocationMapping(); 
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
	 * Questo metodo verifica l'output del metodo getPostsFromRegion
	 * 
	 * @throws Exception
	 */
	@Test
	@DisplayName("Test getPostsFromRegion")
	void getPostsFromRegionTest() throws Exception {
		Location loc = new Location("Firenze","FI","Toscana");
		Post post = null;
		for (Entry<Post, Location> entry : map.entrySet()) {
			if (Objects.equals(loc.toString(), entry.getValue().toString())) {
				post=entry.getKey();
			}
		}
		obj1.put("Message", post.getMessage());
		obj1.put("Created Time", post.getCreatedTime());
		obj2.put("Posts in: toscana", arr.put(obj1));
		assertEquals(service.getPostsFromRegion("toscana").toString(), obj2.toString());
	}
	
}
