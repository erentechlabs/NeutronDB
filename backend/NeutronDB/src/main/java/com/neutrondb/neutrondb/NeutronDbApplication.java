package com.neutrondb.neutrondb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.neutrondb.neutrondb.domain.entities")
@ComponentScan(basePackages = "com.neutrondb.neutrondb")
@EnableJpaRepositories(basePackages = "com.neutrondb.neutrondb.repository")
public class NeutronDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeutronDbApplication.class, args);
    }

}
