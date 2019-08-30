package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Area {
	
	private String id;
	
	private String name;
	
	private List<Area> areas = new ArrayList<>();

}
