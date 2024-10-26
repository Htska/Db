package com.equipo.juegosolimpicos;

import org.springframework.boot.SpringApplication;

public class TestJuegosolimpicosApplication {

	public static void main(String[] args) {
		SpringApplication.from(JuegosolimpicosApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
