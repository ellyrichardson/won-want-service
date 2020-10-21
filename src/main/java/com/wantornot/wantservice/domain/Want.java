package com.wantornot.wantservice.domain;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "want")
public class Want {
	public enum PrivacyType {
		PRIVATE,
		PUBLIC,
		CUSTOM
	}
	
	public enum WantUpdateType {
		NAME,
		POINTS,
		INTEREST_LEVEL,
		NOTES,
		COOLNESS_LEVEL,
		OBTAINED,
		PRIVACY
	}
	
	@Id
	private String wantId;
	private String wantName;
	private String points; // progression
	private String interestLevel;
	private String notes;
	private String coolnessLevel;
	private int commentCount;
	private String ownerName;
	private String ownerId;
	private Set<String> watchers;
	private PrivacyType privacy;
	private WantUpdateType updateType;
	private Timespan timespan; // progression
	// private Notification notification;
	private Boolean obtained; // obtainment
	private ZonedDateTime obtainedDate; // obtainment
	private ZonedDateTime dateCreated; // history
	private ZonedDateTime dateModified; // history
	
	public void setObtained(boolean obtained) {
		this.obtained = obtained;
	}
	
	public boolean isObtained() {
		return this.obtained;
	}
}
