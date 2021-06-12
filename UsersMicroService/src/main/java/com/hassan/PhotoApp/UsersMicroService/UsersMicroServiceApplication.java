package com.hassan.PhotoApp.UsersMicroService;

import feign.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class UsersMicroServiceApplication {


    @Autowired
	Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(UsersMicroServiceApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder BCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

    @Bean
    @Profile("production")
    public String createProdBean() {
		System.out.println("crated production bean wth env : " + environment.getProperty("myapp.env"));
        return "production bean";
    }

    @Bean
    @Profile("default")
    public String createDevBean() {
		System.out.println("crated dev bean wth env : " + environment.getProperty("myapp.env"));

		return "production bean";
    }
}
