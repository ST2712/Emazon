package com.bootcamp_2024_2.emazon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients()
public class EmazonApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmazonApplication.class, args);
	}

}
