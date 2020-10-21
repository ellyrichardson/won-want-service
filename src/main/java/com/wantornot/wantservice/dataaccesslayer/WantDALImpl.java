package com.wantornot.wantservice.dataaccesslayer;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.wantornot.wantservice.domain.Want.PrivacyType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.wantornot.wantservice.domain.Want;

@NoArgsConstructor
@AllArgsConstructor
@Component
public class WantDALImpl implements WantDAL {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Want> getAllWants() {
		return mongoTemplate.findAll(Want.class);
	}

	@Override
	public List<Want> getWantByOwnerId(String ownerId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("ownerId").is(ownerId));
		return mongoTemplate.find(query, Want.class, "want");
	}

	@Override
	public Want addNewWant(Want want) {
		mongoTemplate.save(want);
		// Now, want object will contain the ID as well
		return want;
	}

	@Override
	public void deleteWant(String wantId) {
		// TODO Auto-generated method stub
		mongoTemplate.remove(getWantById(wantId));
	}

	@Override
	public Want getWantById(String wantId) {
		// TODO Auto-generated method stub
		//Query query = new Query();
		//query.addCriteria(Criteria.where("wantId").is(wantId));
		return mongoTemplate.findById(wantId, Want.class, "want");
	}

	@Override
	public Want updateWant(Want want) {
		// TODO Auto-generated method stub
		addNewWant(want);
		return want;
	}
}
