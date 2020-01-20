package com.dz.menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DzMenuAppApplication {

	public static void main(final String[] args) {
		SpringApplication.run(DzMenuAppApplication.class, args);
	}

}
