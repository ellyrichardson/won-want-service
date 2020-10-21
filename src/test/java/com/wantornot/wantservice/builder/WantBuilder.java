package com.wantornot.wantservice.builder;

import java.time.ZonedDateTime;
import java.util.Set;

import com.wantornot.wantservice.domain.Timespan;
import com.wantornot.wantservice.domain.Want;
import com.wantornot.wantservice.domain.Want.PrivacyType;
import com.wantornot.wantservice.domain.Want.WantUpdateType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class WantBuilder {
	private Want want;
	
	public WantBuilder withWantId(String wantId) {
		want.setWantId(wantId);
		return this;
	}
	
	public WantBuilder withOwnerId(String ownerId) {
		want.setOwnerId(ownerId);
		return this;
	}
	
	public WantBuilder withWantName(String wantName) {
		want.setWantName(wantName);
		return this;
	}
	
	public WantBuilder withPoints(String points) {
		want.setPoints(points);
		return this;
	}
	
	public WantBuilder withInterestLevel(String interestLevel) {
		want.setInterestLevel(interestLevel);
		return this;
	}
	
	public WantBuilder withNotes(String notes) {
		want.setNotes(notes);
		return this;
	}
	
	public WantBuilder withCoolnessLevel(String coolnessLevel) {
		want.setCoolnessLevel(coolnessLevel);
		return this;
	}
	
	public WantBuilder withCommentCount(int commentCount) {
		want.setCommentCount(commentCount);
		return this;
	}
	
	public WantBuilder withOwnerName(String ownerName) {
		want.setOwnerName(ownerName);
		return this;
	}
	
	public WantBuilder withWatchers(Set<String> watchers) {
		want.setWatchers(watchers);
		return this;
	}
	
	public WantBuilder withPrivacy(PrivacyType privacy) {
		want.setPrivacy(privacy);
		return this;
	}
	
	public WantBuilder withModification(WantUpdateType updateType) {
		want.setUpdateType(updateType);
		return this;
	}
	
	public WantBuilder withTimespan(Timespan timespan) {
		want.setTimespan(timespan);
		return this;
	}
	
	public WantBuilder withObtained(Boolean obtained) {
		want.setObtained(obtained);
		return this;
	}
	
	public WantBuilder withObtainedDate(ZonedDateTime obtainedDate) {
		want.setObtainedDate(obtainedDate);
		return this;
	}
	
	public WantBuilder withDateCreated(ZonedDateTime dateCreated) {
		want.setDateCreated(dateCreated);
		return this;
	}
	
	public WantBuilder withDateModified(ZonedDateTime dateModified) {
		want.setDateModified(dateModified);
		return this;
	}
	
	public Want build() {
		return this.want;
	}
}
