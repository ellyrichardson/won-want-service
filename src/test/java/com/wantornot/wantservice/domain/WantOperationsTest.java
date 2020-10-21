package com.wantornot.wantservice.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.Test;

import com.wantornot.wantservice.builder.WantBuilder;
import com.wantornot.wantservice.domain.Want.PrivacyType;
import com.wantornot.wantservice.domain.Want.WantUpdateType;
import com.wantornot.wantservice.domain.operations.WantOperations;

/*
 * TODO: Test  all the attributes (not just updating attributes), 
 * just  for in the  future if something changes
 * */

public class WantOperationsTest {
	private static final String COOLNESS_LEVEL = "100";
	private static final String INTEREST_LEVEL = "120";
	private static final String NOTES = "notesVal";
	private static final String NAME = "nameVal";
	private static final String POINTS = "pointsVal";
	private static final String NEW_COOLNESS_LEVEL = "120";
	private static final String NEW_INTEREST_LEVEL = "140";
	private static final String NEW_NOTES = "NewnotesVal";
	private static final String NEW_NAME = "NewnameVal";
	private static final String NEW_POINTS = "NewpointsVal";
	
	@Test
	public void testUpdateWantByUpdateType() {
		Want mockWant = createNewWant();
		testInitialWantValues(mockWant);
		updateWantValues(mockWant);
		testNewWantValues(mockWant);
	}
	
	private void testInitialWantValues(Want mockWant) {
		assertEquals(NAME, mockWant.getWantName());
		assertEquals(POINTS, mockWant.getPoints());
		assertEquals(INTEREST_LEVEL, mockWant.getInterestLevel());
		assertEquals(NOTES, mockWant.getNotes());
		assertEquals(COOLNESS_LEVEL, mockWant.getCoolnessLevel());
		assertEquals(false, mockWant.isObtained());
		assertEquals(PrivacyType.PRIVATE, mockWant.getPrivacy());
	}
	
	private void testNewWantValues(Want mockWant) {
		assertEquals(NEW_NAME, mockWant.getWantName());
		assertEquals(NEW_POINTS, mockWant.getPoints());
		assertEquals(NEW_INTEREST_LEVEL, mockWant.getInterestLevel());
		assertEquals(NEW_NOTES, mockWant.getNotes());
		assertEquals(NEW_COOLNESS_LEVEL, mockWant.getCoolnessLevel());
		assertEquals(true, mockWant.isObtained());
		assertEquals(PrivacyType.PUBLIC, mockWant.getPrivacy());
	}
	
	private void updateWantValues(Want mockWant) {
		WantOperations target = new WantOperations();
		target.updateWantByType(mockWant, WantUpdateType.NAME, NEW_NAME);
		target.updateWantByType(mockWant, WantUpdateType.POINTS, NEW_POINTS);
		target.updateWantByType(mockWant, WantUpdateType.INTEREST_LEVEL, NEW_INTEREST_LEVEL);
		target.updateWantByType(mockWant, WantUpdateType.NOTES, NEW_NOTES);
		target.updateWantByType(mockWant, WantUpdateType.COOLNESS_LEVEL, NEW_COOLNESS_LEVEL);
		target.updateWantByType(mockWant, WantUpdateType.OBTAINED, true);
		target.updateWantByType(mockWant, WantUpdateType.PRIVACY, PrivacyType.PUBLIC);
	}
	
	private Want createNewWant() {
		WantBuilder wantBuilder = new WantBuilder(new Want());
		return wantBuilder.withWantName(NAME)
				.withInterestLevel(INTEREST_LEVEL)
				.withPoints(POINTS)
				.withNotes(NOTES)
				.withCoolnessLevel(COOLNESS_LEVEL)
				.withObtained(false)
				.withPrivacy(PrivacyType.PRIVATE)
				.build();
	}
}
