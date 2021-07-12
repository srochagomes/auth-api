package br.com.rd.authbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication(scanBasePackages = {"br.com.rd"})
public class AuthbaseApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthbaseApplication.class, args);
	}
}