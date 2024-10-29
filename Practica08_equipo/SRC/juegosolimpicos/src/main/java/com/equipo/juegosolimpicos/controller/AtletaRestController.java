package com.equipo.juegosolimpicos.controller;

import com.equipo.juegosolimpicos.repository.AtletaRepository;
import com.equipo.juegosolimpicos.model.Atleta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AtletaRestController {
    @Autowired
    AtletaRepository atletaRepository;

    /**
     * Método para añadir un atleta en donde se crea la ruta /atleta por medio
     * de @PostMapping el cuál escucha las peticiones POST.
     * 
     * @param atleta objeto de tipo Atleta el cual guardaremos en la base de datos.
     * @return el objeto de tipo Atleta guardado en la base de datos.
     */
    @PostMapping("/atleta")
    public Atleta addAtleta(@RequestBody Atleta atleta) {
        return atletaRepository.saveAtleta(atleta);
    }

    /**
     * Método para actualizar un atleta en donde se crea la ruta /atleta por medio
     * de @PutMapping el cuál escucha las peticiones PUT.
     * 
     * @param atleta objeto de tipo Atleta el cual actualizaremos en la base de
     *               datos.
     * @return el objeto de tipo Atleta actualizado en la base de datos.
     */
    @PutMapping("/atleta")
    public Atleta updateAtleta(@RequestBody Atleta atleta) {
        return atletaRepository.updateAtleta(atleta);
    }

    /**
     * Método para obtener un atleta en donde se crea la ruta
     * /atleta/{numeroPasaporte} por medio de @GetMapping el cuál escucha las
     * peticiones GET.
     * 
     * @param numeroPasaporte número de pasaporte del atleta que se desea obtener.
     *                        (se utilizará como parte de PathVariable)
     * @return el objeto de tipo Atleta que se obtuvo de la base de datos.
     */
    @GetMapping("/atleta/{numeroPasaporte}")
    public Atleta getAtleta(@PathVariable("numeroPasaporte") String numeroPasaporte) {
        return atletaRepository.getAtletaByNumeroPasaporte(numeroPasaporte);
    }

    /**
     * Método para eliminar un atleta en donde se crea la ruta
     * /atleta/{numeroPasaporte} por medio de @DeleteMapping el cuál escucha las
     * peticiones DELETE.
     * 
     * @param numeroPasaporte número de pasaporte del atleta que se desea eliminar.
     *                        (se utilizará como parte de PathVariable)
     * @return Mensaje del estado de la eliminación del atleta.
     */
    @DeleteMapping("/atleta/{numeroPasaporte}")
    public String deleteAtleta(@PathVariable("numeroPasaporte") String numeroPasaporte) {
        return atletaRepository.deleteAtletaByNumeroPasaporte(numeroPasaporte);
    }

    @GetMapping("/atleta")
    /**
     * Método para obtener todos los atletas en donde se crea la ruta /atleta por
     * medio de @GetMapping el cuál escucha las peticiones GET.
     * 
     * @return Lista de objetos de tipo Atleta que contiene todos los atletas de la
     *         base de datos.
     */
    public List<Atleta> getAtletas() {
        return atletaRepository.getAllAtletas();
    }
}