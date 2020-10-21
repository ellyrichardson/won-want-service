package com.wantornot.wantservice.packager;

import com.wantornot.wantservice.builder.WantBuilder;
import com.wantornot.wantservice.domain.Want;
import com.wantornot.wantservice.domain.Want.PrivacyType;

public class WantPackager {
	public static Want packageBasicWant() {
		WantBuilder wantBuilder = new WantBuilder(new Want());
		return wantBuilder.withWantName("wantItem")
			.withOwnerName("some owner")
			.withPoints("1000")
			.withInterestLevel("25")
			.withNotes("notes for want")
			.withCoolnessLevel("500")
			.withPrivacy(PrivacyType.PRIVATE)
			.withObtained(false)
			.withWantId("id000001")
			.withOwnerId("owner01")
			.build();
	}
	
	public static Want packageObtainedWant() {
		WantBuilder wantBuilder = new WantBuilder(new Want());
		return wantBuilder.withWantName("wantItem")
			.withOwnerName("some owner")
			.withPoints("1000")
			.withInterestLevel("25")
			.withNotes("notes for want")
			.withCoolnessLevel("500")
			.withPrivacy(PrivacyType.PRIVATE)
			.withObtained(true)
			.withWantId("id000002")
			.withOwnerId("owner02")
			.build();
	}
}
