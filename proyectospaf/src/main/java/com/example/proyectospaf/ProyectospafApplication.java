package com.example.proyectospaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProyectospafApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectospafApplication.class, args);
	}

}
