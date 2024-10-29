package com.equipo.juegosolimpicos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p> Clase que implementa las acciones del programa. </p>
 * @author equipo.
 */
@Controller
public class MainController {

    /** 
     * Regresa la página principal de la aplicación de Juegos Olímpicos.
     * @return String La página principal.
     */
    @GetMapping("")
    public String showHomePage() {
        return "index";
    }
}
