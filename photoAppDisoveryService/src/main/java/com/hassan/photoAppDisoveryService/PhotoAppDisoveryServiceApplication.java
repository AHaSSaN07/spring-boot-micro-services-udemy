package com.hassan.photoAppDisoveryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PhotoAppDisoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAppDisoveryServiceApplication.class, args);
	}

}
