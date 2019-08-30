package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Building {
	
	@NotNull
	@Id
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

}
