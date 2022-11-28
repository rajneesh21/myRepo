package com.pmsapigateway.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEurekaClient
public class PmsApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmsApigatewayApplication.class, args);
	}

}
