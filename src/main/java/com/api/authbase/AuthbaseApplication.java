package com.api.authbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages = {"com.api"})
public class AuthbaseApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthbaseApplication.class, args);
	}
}