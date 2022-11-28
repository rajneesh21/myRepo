package com.pms.observation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaClient
public class PmsObservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsObservationApplication.class, args);
	}

}
