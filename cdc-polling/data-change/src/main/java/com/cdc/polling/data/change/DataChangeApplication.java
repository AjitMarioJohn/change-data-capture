package com.cdc.polling.data.change;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.cdc.polling.data.change",
        "com.cdc.polling.data.model"
})
@EntityScan(basePackages = "com.cdc.polling.data.model.entities")
@EnableJpaRepositories(basePackages = {"com.cdc.polling.data.model.repositories"})
public class DataChangeApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataChangeApplication.class, args);
    }
}
