package com.wantornot.wantservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wantornot.wantservice.dataaccesslayer.WantDAL;
import com.wantornot.wantservice.dataaccesslayer.WantRepository;
import com.wantornot.wantservice.domain.Want;
import com.wantornot.wantservice.domain.Want.WantUpdateType;
import com.wantornot.wantservice.domain.operations.WantOperations;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping(value = "/")
public class WantController {
	//private final Logger LOG = LoggerFactory.getLogger(getClass());

	//private final WantRepository wantRepository;
	private final WantDAL wantDAL;

	public WantController(WantDAL wantDAL) {
		//this.wantRepository = wantRepository;
		this.wantDAL = wantDAL;
	}
	
	/*
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Want> getAllWants() {
		//LOG.info("Getting all users.");
		return wantRepository.findAll();
	}*/
	
	/*
	@RequestMapping(value = "/{wantId}", method = RequestMethod.GET)
	public Optional<Want> getWant(@PathVariable String wantId) {
		//LOG.info("Getting user with ID: {}.", userId);
		return wantRepository.findById(wantId);
	}*/
	
	@RequestMapping(value = "/{ownerId}", method = RequestMethod.GET)
	public Object getWantByOwnerId(@PathVariable String ownerId) {
		/*  
		 * TODO: 
		 * Add a checker if the logged in user was the one asking for security. 
		 * Example, an if statement that first checks for Token before 
		 * proceeding to getting the ownerID.
		 */
		return wantDAL.getWantByOwnerId(ownerId);
	}
	
	@RequestMapping(value = "/want/{wantId}", method = RequestMethod.GET)
	public Object getWantById(@PathVariable String wantId) {
		/*  
		 * TODO: 
		 * Add a checker if the logged in user was the one asking for security. 
		 * Example, an if statement that first checks for Token before 
		 * proceeding to getting the ownerID.
		 */
		return wantDAL.getWantById(wantId);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Want addNewWant(Want want) {
		//LOG.info("Saving user.");
		return wantDAL.addNewWant(want);
	}
	
	@RequestMapping(value = "/delete/{wantId}", method = RequestMethod.DELETE)
	public String deleteWant(String wantId) {
		//wantRepository.deleteById(wantId);
		wantDAL.deleteWant(wantId);
		return wantId;
	}
	
	@RequestMapping(value = "/{wantId}/update/{updateType}/{updateValue}", method = RequestMethod.POST)
	public Want updateWant(String wantId, WantUpdateType wantUpdateType, Object wantUpdateValue) {
		
		Want want = wantDAL.getWantById(wantId);
		WantOperations wantOps = new WantOperations();
		wantOps.updateWantByType(want, wantUpdateType, wantUpdateValue);
		
		//return wantRepository.save(want);
		return wantDAL.updateWant(want);
	}
}
