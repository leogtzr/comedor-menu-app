package com.dz.menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DZMenuAppApplication {
	public static void main(final String[] args) {
		SpringApplication.run(DZMenuAppApplication.class, args);
	}
}
