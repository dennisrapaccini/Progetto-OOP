package com.project.MetaStats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.MetaStats.service.ServiceImpl;

@SpringBootApplication
public class MetaStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetaStatsApplication.class, args);
		ServiceImpl service = new ServiceImpl();
		service.getPost_User();
		service.getMessage_Post();
	}

}
