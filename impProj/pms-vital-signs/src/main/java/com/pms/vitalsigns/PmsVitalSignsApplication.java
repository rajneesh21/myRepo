package com.pms.vitalsigns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaClient
public class PmsVitalSignsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsVitalSignsApplication.class, args);
	}

}
