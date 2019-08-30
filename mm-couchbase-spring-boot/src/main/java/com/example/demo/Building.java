package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

@Document
public class Building {
	
	@NotNull
	@Id
	@Field
	private String id;
	
	@NotNull
	@Field
	private String name;
	
	@NotNull
	@Field
	private String companyId;
	
	@Field
	private List<Area> areas = new ArrayList<>();
	
	@Field
	private List<String> phoneNumbers = new ArrayList<>();

	public Building(String id, String name, String companyId, List<Area> areas, List<String> phoneNumbers) {
		super();
		this.id = id;
		this.name = name;
		this.companyId = companyId;
		this.areas = areas;
		this.phoneNumbers = phoneNumbers;
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public List<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

}
