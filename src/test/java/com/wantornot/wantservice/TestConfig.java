package com.wantornot.wantservice;

import java.io.IOException;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.wantornot.wantservice.dataaccesslayer.WantDAL;
import com.wantornot.wantservice.dataaccesslayer.WantDALImpl;
import com.wantornot.wantservice.dataaccesslayer.WantRepository;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.exceptions.DistributionException;
import de.flapdoodle.embed.process.runtime.Network;

@Configuration
@EnableMongoRepositories("com.wantornot.wantservice")
@ComponentScan("com.wantornot.wantservice")
public class TestConfig {
	private static final String CONNECTION_STRING = "mongodb://%s:%d";
	//private static final String MONGO_DB_URL = "localhost";
    //private static final String MONGO_DB_NAME = "embeded_db";

	private String ip = "localhost";
	
	private int port = 27019;
    
	/*
    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
    	MongodExecutable mongodExecutable;
        MongoTemplate mongoTemplate;
    	//String ip = "localhost";
        //int port = 27017;
 
        IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
            .net(new Net(ip, port, Network.localhostIsIPv6()))
            .build();
 
        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
        mongoTemplate = new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, ip, port)), "want");
    	return mongoTemplate;
        /*
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(MONGO_DB_URL);
        MongoClient mongoClient = (MongoClient) mongo.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
        return mongoTemplate;
    }*/
    
    @Bean(destroyMethod = "stop")
    public MongodExecutable mongodExecutable() throws DistributionException, UnknownHostException, IOException {
    	MongodStarter starter = MongodStarter.getDefaultInstance();
        return starter.prepare(mongodConfig());
    }
    
    @Bean
    public MongoClient mongodClient() {
    	return MongoClients.create(String.format(CONNECTION_STRING, ip, port));
    }
    
    @Bean 
    public IMongodConfig mongodConfig() throws UnknownHostException, IOException {
    	return new MongodConfigBuilder().version(Version.Main.PRODUCTION)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();
    }
    
    @Bean
    public MongoTemplate mongoTemplate() {
    	return new MongoTemplate(mongodClient(), "want");
    }
    
    @Bean
    @Primary
    public WantDALImpl testWantDALImpl() throws IOException {
    	return new WantDALImpl(mongoTemplate());
    }
    
    // Maybe this should be removed?
    /*
    @Bean
    public Mongo mongo() throws IOException {
        System.setProperty("DB.TRACE","true");
        return new EmbeddedMongoBuilder()
            .version("2.13.1")
            .bindIp("127.0.0.1")
            .port(999)
            .build();
    }*/
}
