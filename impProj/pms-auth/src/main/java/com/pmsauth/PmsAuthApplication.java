package com.pmsauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PmsAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsAuthApplication.class, args);
	}

}
