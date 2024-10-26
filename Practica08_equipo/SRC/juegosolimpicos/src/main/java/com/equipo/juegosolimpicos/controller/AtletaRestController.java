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

    @PostMapping("/atleta")
    public Atleta addAtleta(@RequestBody Atleta atleta) {
        return atletaRepository.saveAtleta(atleta);
    }

    @PutMapping("/atleta")
    public Atleta updateAtleta(@RequestBody Atleta atleta) {
        return atletaRepository.updateAtleta(atleta);
    }

    @GetMapping("/atleta/{numeroPasaporte}")
    public Atleta getAtleta(@PathVariable("numeroPasaporte") String numeroPasaporte) {
        return atletaRepository.getAtletaByNumeroPasaporte(numeroPasaporte);
    }

    @DeleteMapping("/atleta/{numeroPasaporte}")
    public String deleteAtleta(@PathVariable("numeroPasaporte") String numeroPasaporte) {
        return atletaRepository.deleteAtletaByNumeroPasaporte(numeroPasaporte);
    }

    @GetMapping("/atleta")
    public List<Atleta> getAtletas() {
        return atletaRepository.getAllAtletas();
    }
}