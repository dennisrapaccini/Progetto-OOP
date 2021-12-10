package com.project.MetaStats.filtersManagement;
import java.util.ArrayList;
import com.project.MetaStats.model.Post;
import com.project.MetaStats.service.ServiceImpl;

public class FilterCity extends Filter{
	
	ServiceImpl service = new ServiceImpl();
	String prova = service.getMessage_Post();
	
	private String city;
	
	public FilterCity(String city) {
		super();
		this.city = city;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String filter() {
		return city;
	}
	
	
	
	
}
