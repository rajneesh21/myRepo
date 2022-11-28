package com.pms.allergy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaClient
public class PmsAllergyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsAllergyApplication.class, args);
	}

}
