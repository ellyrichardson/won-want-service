package com.wantornot.wantservice.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.wantornot.wantservice.TestConfig;
import com.wantornot.wantservice.dataaccesslayer.WantDALImpl;
import com.wantornot.wantservice.domain.Want;
import com.wantornot.wantservice.domain.Want.PrivacyType;
import com.wantornot.wantservice.domain.Want.WantUpdateType;
import com.wantornot.wantservice.packager.WantPackager;

import de.flapdoodle.embed.mongo.MongodExecutable;

@SpringBootTest
@Import(TestConfig.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class WantControllerIntegrationTest {
	
	@Autowired
	private WantDALImpl testWantDALImpl;
	
	@Autowired
	private MongoTemplate testMongoTemplate;
	
	@Autowired
	private MongodExecutable testMongodExecutable;
	
	private WantController target;
	
	@AfterEach
    public void clean() {
		testMongodExecutable.stop();
    }
	
	@BeforeEach
	public void setup() throws UnknownHostException, IOException {
		testMongodExecutable.start();
		testMongoTemplate.dropCollection("want");
		target = new WantController(testWantDALImpl);
	}
	
	// TODO: Fix these tests to have appropriate arguments and other stuff (Add WATCHERS test)
	@Test
	@SuppressWarnings("unchecked")
	public void testGetWantByOwnerId() {
		// Data Setup
		Want testWant = WantPackager.packageBasicWant();
		Want testWant2 = WantPackager.packageObtainedWant();
		testWant2.setOwnerId(testWant.getOwnerId());
		
		// PreTest Assertions
		List<Want> retrievedWants = (List<Want>) target.getWantByOwnerId(testWant.getOwnerId());
		assertEquals(0, retrievedWants.size());
		
		target.addNewWant(testWant);
		target.addNewWant(testWant2);
		
		// Testing
		retrievedWants = (List<Want>) target.getWantByOwnerId(testWant.getOwnerId());
		assertEquals(2, retrievedWants.size());
		for (int i = 0; i < retrievedWants.size(); i++) {
			switch(i) {
			case 1:
				assertTrue(retrievedWants.stream().anyMatch(item -> testWant2.getWantId().equals(item.getWantId())));
				break;
			default:
				assertTrue(retrievedWants.stream().anyMatch(item -> testWant.getWantId().equals(item.getWantId())));
			}
		}
		
		// Deletions / Cleanup
		target.deleteWant(testWant.getWantId());
		assertNull(target.getWantById(testWant.getWantId()));
		target.deleteWant(testWant2.getWantId());
		assertNull(target.getWantById(testWant2.getWantId()));
	}
	
	@Test
	public void testGetWantById() {
		// Data Setup
		Want testWant = WantPackager.packageBasicWant();
		target.addNewWant(testWant);
		
		// Testing
		Want retrievedWant = (Want) target.getWantById(testWant.getWantId());
		assertEquals(testWant.getWantId(), retrievedWant.getWantId());
		target.deleteWant(testWant.getWantId());
		assertNull(target.getWantById(testWant.getWantId()));
	}
	
	@Test
	public void testAddNewWant() {
		// Data Setup
		Want testWant = WantPackager.packageBasicWant();
		
		// Testing
		target.addNewWant(testWant);
		Want retrievedWant = (Want) target.getWantById(testWant.getWantId());
		assertEquals(testWant.getWantId(), retrievedWant.getWantId());
		target.deleteWant(testWant.getWantId());
		assertNull(target.getWantById(testWant.getWantId()));
	}
	
	@Test
	public void testUpdateWant() {
		String nameUpdate = "daNEWNAME";
		String notesUpdate = "newNotez";
		String coolnessLevelUpdate = "123456";
		String interestLevelUpdate = "5678";
		String pointsUpdate = "6733";
		
		// Data Setup
		Want testWant = WantPackager.packageBasicWant();
		testWant.setPrivacy(PrivacyType.PUBLIC);
		target.addNewWant(testWant);
		Want retrievedWant = (Want) target.getWantById(testWant.getWantId());
		assertEquals(testWant.getWantId(), retrievedWant.getWantId());
		
		// Testing
		target.updateWant(testWant.getWantId(), WantUpdateType.NAME, nameUpdate);
		retrievedWant = (Want) target.getWantById(testWant.getWantId());
		assertEquals(nameUpdate, retrievedWant.getWantName());
		
		target.updateWant(testWant.getWantId(), WantUpdateType.NOTES, notesUpdate);
		retrievedWant = (Want) target.getWantById(testWant.getWantId());
		assertEquals(notesUpdate, retrievedWant.getNotes());
		
		target.updateWant(testWant.getWantId(), WantUpdateType.COOLNESS_LEVEL, coolnessLevelUpdate);
		retrievedWant = (Want) target.getWantById(testWant.getWantId());
		assertEquals(coolnessLevelUpdate, retrievedWant.getCoolnessLevel());
		
		target.updateWant(testWant.getWantId(), WantUpdateType.INTEREST_LEVEL, interestLevelUpdate);
		retrievedWant = (Want) target.getWantById(testWant.getWantId());
		assertEquals(interestLevelUpdate, retrievedWant.getInterestLevel());
		
		target.updateWant(testWant.getWantId(), WantUpdateType.OBTAINED, true);
		retrievedWant = (Want) target.getWantById(testWant.getWantId());
		assertEquals(true, retrievedWant.isObtained());
		
		target.updateWant(testWant.getWantId(), WantUpdateType.POINTS, pointsUpdate);
		retrievedWant = (Want) target.getWantById(testWant.getWantId());
		assertEquals(pointsUpdate, retrievedWant.getPoints());
		
		target.updateWant(testWant.getWantId(), WantUpdateType.PRIVACY, PrivacyType.PUBLIC);
		retrievedWant = (Want) target.getWantById(testWant.getWantId());
		assertEquals(PrivacyType.PUBLIC, retrievedWant.getPrivacy());
		
		// Cleaning Up
		target.deleteWant(testWant.getWantId());
		assertNull(target.getWantById(testWant.getWantId()));
	}
}
