package com.example.demo;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BuildingServiceIntegrationTest extends SampleApplicationTests {

    public static final String BUILDING_1 = "building::1";
    public static final String COMPANY_2 = "company::2";

    @Autowired
    private BuildingService buildingService;

    @Test
    public void testSave() {
        Building building = new Building(BUILDING_1, "couchbase",
                COMPANY_2, new ArrayList<>(), new ArrayList<>());
        buildingService.save(building);

        Optional<Building> newBuilding = buildingService.findById(BUILDING_1);
        assertThat(newBuilding, equalTo(building));
    }

    @Test(expected = ConstraintViolationException.class)
    public void testSaveWithMissingNameField() {
        Building building = new Building("building::1", null,
                COMPANY_2, new ArrayList<>(), new ArrayList<>());
        buildingService.save(building);
    }

    @Test
    public void testFindByCompanyId() {
        Building building = new Building(BUILDING_1, "building",
                COMPANY_2, new ArrayList<>(), new ArrayList<>());
        buildingService.save(building);

        List<Building> newBuildings = buildingService.findByCompanyId(COMPANY_2);
        assertThat(newBuildings, equalTo(Arrays.asList(building)));
    }

    @Test
    public void testFindByCompanyIdAndNameLike() {
        String buildingName = "couchbase";
        Building building = new Building(BUILDING_1, "couchbase",
                COMPANY_2, new ArrayList<>(), new ArrayList<>());
        buildingService.save(building);

        Building building2 = new Building("building::4", "AnotherBuilding",
                COMPANY_2, new ArrayList<>(), new ArrayList<>());
        buildingService.save(building2);

        List<Building> newBuildings = buildingService.findByCompanyIdAndNameLike(COMPANY_2, "cou%", 0);

        assertThat(newBuildings, hasSize(1));
        assertThat(buildingName, equalTo(newBuildings.get(0).getName()));
    }


    @Test
    public void testFindArea2LevelsDeep(){
        String targetAreaId = "ref1ref1";

        List<Area> areas = Arrays.asList(
                new Area("ref1","ref1name", Arrays.asList(
                        new Area(targetAreaId,"ref1ref1name", new ArrayList<>()))),
                new Area("ref2","ref2name",  new ArrayList<>()));
        Building building1 = new Building(BUILDING_1, "couchbase",
                COMPANY_2, areas, new ArrayList<>());

        List<Area> areas2 = Arrays.asList(
                new Area("ref3","ref3name", new ArrayList<>()),
                new Area("ref4","ref4name", new ArrayList<>()));
        Building building2 =  new Building("buildingId::2", "couchbase manchester",
                "companyId::1", areas2, new ArrayList<>());

        buildingService.save(building1);
        buildingService.save(building2);

        Building foundBusiness = buildingService.findByCompanyAndAreaId(COMPANY_2, targetAreaId);

        assertThat(foundBusiness, equalTo(building1));
    }

    @Test
    public void testFindPhoneNumber() {
        String targetPhoneNumber = "+44 (0) 203 837 9130";
        Building building = new Building(BUILDING_1, "building",
                COMPANY_2, new ArrayList<>(), Arrays.asList(targetPhoneNumber,
                "1-650-417-7500", "+1 (415) 963-4174", "1-650-417-7500", "1-650-964-7935"));
        buildingService.save(building);

        Building building2 = new Building("building::3", "Some other building",
                COMPANY_2, new ArrayList<>(), Arrays.asList("+44 (0) 333 837 9130", "5-750-417-7500"));
        buildingService.save(building2);

        List<Building> foundBuildings = buildingService.findByPhoneNumber(targetPhoneNumber);

        assertThat(foundBuildings, hasSize(1));
        assertThat(foundBuildings.get(0).getId(), equalTo(BUILDING_1));
    }

    @Test
    public void testCount() {
    	Building building = new Building(BUILDING_1, "building1",
                COMPANY_2, new ArrayList<>(), new ArrayList<>());
        buildingService.save(building);

        Building building2 = new Building("building::4", "building2",
                COMPANY_2, new ArrayList<>(), new ArrayList<>());
        buildingService.save(building2);

        Building buildingFromOtherCompany = new Building("building::5", "buildingFromOtherCompany",
                "company::5", new ArrayList<>(), new ArrayList<>());
        buildingService.save(buildingFromOtherCompany);

        Long count = buildingService.countBuildings(COMPANY_2);

        assertTrue(count == 2);
    }
}