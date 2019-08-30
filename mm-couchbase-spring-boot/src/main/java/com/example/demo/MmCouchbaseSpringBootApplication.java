package com.example.demo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MmCouchbaseSpringBootApplication implements CommandLineRunner {
	
	public static final String BUILDING_1 = "building::1";
    public static final String COMPANY_2 = "company::2";
	
    @Autowired
    private BuildingService buildingService;

	public static void main(String[] args) {
		SpringApplication.run(MmCouchbaseSpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Building building = new Building(BUILDING_1, "couchbase",
                COMPANY_2, new ArrayList<>(), new ArrayList<>());
        buildingService.save(building);
		
	}

}
