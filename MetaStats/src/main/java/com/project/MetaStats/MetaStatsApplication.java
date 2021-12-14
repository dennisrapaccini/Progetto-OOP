package com.project.MetaStats;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.MetaStats.filtersManagement.FileManagement;
import com.project.MetaStats.filtersManagement.FilterCity;
import com.project.MetaStats.model.Post;
import com.project.MetaStats.service.ServiceImpl;
import com.project.MetaStats.service.ToJSON;
import com.project.MetaStats.statistics.Statistics;

@SpringBootApplication
public class MetaStatsApplication {

	public static void main(String[] args) throws Exception {
		
		//ArrayList<Post> p = new ArrayList<Post>();
		SpringApplication.run(MetaStatsApplication.class, args);
		ServiceImpl service = new ServiceImpl();
		//service.getPost_User();
		//service.getMessage_Post();
		FilterCity filtercity= new FilterCity();
		FileManagement file= new FileManagement();
		//JSONArray ciao = filtercity.getPostsfromCity("Treia"); //Test 
		//file.getFile();
		service.PostLocationMapping();
		//ToJSON tj = new ToJSON();//test
		//tj.toJSONArray(p);//test
		Statistics stats = new Statistics();
		//stats.rankingCity();
		service.getPostsFromProvince("MT");
		//System.out.println(service.allPosts());
		
	}
		
}
