package com.pms.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PmsUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsUserApplication.class, args);
	}

}
