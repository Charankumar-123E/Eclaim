package com.ac.dha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.ac.dha.entity")
@EnableJpaRepositories("com.ac.dha.repository")
public class DhaApplication {
	public static void main(String[] args) {
		SpringApplication.run(DhaApplication.class, args);
	}
}
