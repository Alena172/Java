package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableJpaRepositories(basePackages = "com.example")
@EntityScan(basePackages = "com.example")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
