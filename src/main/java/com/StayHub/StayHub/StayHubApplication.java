package com.StayHub.StayHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StayHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(StayHubApplication.class, args);
	}

}
