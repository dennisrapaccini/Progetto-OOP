package com.project.MetaStats;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.MetaStats.filtersManagement.FileManagement;
import com.project.MetaStats.filtersManagement.FilterCity;
import com.project.MetaStats.service.ServiceImpl;

@SpringBootApplication
public class MetaStatsApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MetaStatsApplication.class, args);
		ServiceImpl service = new ServiceImpl();
		//service.getPost_User();
		//service.getMessage_Post();
		FilterCity filtercity= new FilterCity();
		FileManagement file= new FileManagement();
		//JSONArray ciao = filtercity.getPostsfromCity("Treia"); //Test 
		//file.getFile();
		service.PostLocationMap();
	}
		
}
