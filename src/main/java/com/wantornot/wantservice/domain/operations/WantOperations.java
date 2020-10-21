package com.wantornot.wantservice.domain.operations;

import java.time.ZonedDateTime;
import java.util.Set;

import com.wantornot.wantservice.domain.Want;
import com.wantornot.wantservice.domain.Want.PrivacyType;
import com.wantornot.wantservice.domain.Want.WantUpdateType;

public class WantOperations {
	@SuppressWarnings("unchecked")
	public void updateWantByType(Want want, WantUpdateType updateType, Object updateValue) {
		switch(updateType) {
		case COOLNESS_LEVEL:
			want.setCoolnessLevel((String) updateValue);
			break;
		case INTEREST_LEVEL:
			want.setInterestLevel((String) updateValue);
			break;
		case NOTES:
			want.setNotes((String) updateValue);
			break;
		case OBTAINED:
			want.setObtained((boolean) updateValue);
			break;
		case POINTS:
			want.setPoints((String) updateValue);
			break;
		case PRIVACY:
			want.setPrivacy((PrivacyType) updateValue);
			break;
		default:
			want.setWantName((String) updateValue);
		}
	}
}
