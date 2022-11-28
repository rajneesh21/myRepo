package com.pms.pmspatientvisit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PmsPatientVisitApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsPatientVisitApplication.class, args);
	}

}
