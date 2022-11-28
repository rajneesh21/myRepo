package com.pms.pmsschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PmsScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsScheduleApplication.class, args);
	}

}
