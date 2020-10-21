package com.wantornot.wantservice.dataaccesslayer;

import java.util.List;

import com.wantornot.wantservice.domain.Want;

public interface WantDAL {
	List<Want> getAllWants();
	
	Want getWantById(String wantId);

	List<Want> getWantByOwnerId(String ownerId);

	Want addNewWant(Want want);
	
	Want updateWant(Want want);
	
	void deleteWant(String wantId);
}
