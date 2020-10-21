package com.wantornot.wantservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@EnableEncryptableProperties
@SpringBootApplication
public class WantServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WantServiceApplication.class, args);
	}

}
