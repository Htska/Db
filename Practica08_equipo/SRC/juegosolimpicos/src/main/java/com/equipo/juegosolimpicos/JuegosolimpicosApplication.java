package com.equipo.juegosolimpicos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

/**
 * <p> Clase que ejecuta el programa. </p>
 * @author equipo.
 */
public class JuegosolimpicosApplication {

	/** 
	 * Método principal. Corre la aplicación de Juegos Olímpicos.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(JuegosolimpicosApplication.class, args);
	}

}
