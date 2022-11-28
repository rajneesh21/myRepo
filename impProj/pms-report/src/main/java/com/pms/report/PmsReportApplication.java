package com.pms.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaClient
public class PmsReportApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsReportApplication.class, args);
	}

}
