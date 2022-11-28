package com.pms.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaClient
public class PmsAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsAdminApplication.class, args);
	}

}
