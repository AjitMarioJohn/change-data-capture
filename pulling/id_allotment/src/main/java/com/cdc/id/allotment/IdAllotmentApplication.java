package com.cdc.id.allotment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableJpaRepositories
public class IdAllotmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdAllotmentApplication.class, args);
	}

}
