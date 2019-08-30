package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Area {
	
	private String id;
	
	private String name;
	
	private List<Area> areas = new ArrayList<>();

	public Area(String id, String name, List<Area> areas) {
		super();
		this.id = id;
		this.name = name;
		this.areas = areas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
	
	

}
