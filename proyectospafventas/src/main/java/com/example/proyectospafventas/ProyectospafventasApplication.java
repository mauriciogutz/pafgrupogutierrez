package com.example.proyectospafventas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class ProyectospafventasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectospafventasApplication.class, args);
	}

}
