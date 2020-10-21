package com.wantornot.wantservice.dataaccesslayer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.wantornot.wantservice.domain.Want;

public interface WantRepository extends MongoRepository<Want, String> {
}
