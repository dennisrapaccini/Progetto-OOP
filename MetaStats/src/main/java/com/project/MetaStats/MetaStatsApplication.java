package com.project.MetaStats;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.MetaStats.filtersManagement.FileManagement;
import com.project.MetaStats.service.ServiceImpl;

@SpringBootApplication
public class MetaStatsApplication {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		SpringApplication.run(MetaStatsApplication.class, args);
		ServiceImpl service = new ServiceImpl();
		service.getPost_User();
		service.getMessage_Post();
		FileManagement file  = new FileManagement();
		file.getFile();
	}
	
}
