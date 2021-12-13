package com.project.MetaStats.model;

import java.util.HashMap;

public class SuperPost {
	private HashMap<Post,Location>  postLocation = new HashMap<Post,Location>();
	

	public HashMap<Post, Location> getPostLocation() {
		return postLocation;
	}

	public void setPostLocation(HashMap<Post, Location> postLocation) {
		this.postLocation = postLocation;
	}

	

}
