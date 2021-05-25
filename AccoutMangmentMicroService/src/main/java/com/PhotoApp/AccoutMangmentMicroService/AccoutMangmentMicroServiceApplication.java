package com.PhotoApp.AccoutMangmentMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AccoutMangmentMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccoutMangmentMicroServiceApplication.class, args);
	}

}
