package com.equipo.juegosolimpicos.controller;

import com.equipo.juegosolimpicos.repository.DisciplinaRepository;
import com.equipo.juegosolimpicos.model.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class DisciplinaRestController {
    @Autowired
    DisciplinaRepository disciplinaRepository;

    @PostMapping("/disciplina")
    public Disciplina addDisciplina(@RequestBody Disciplina disciplina) {
        return disciplinaRepository.saveDisciplina(disciplina);
    }

    @PutMapping("/disciplina")
    public Disciplina updateDisciplina(@RequestBody Disciplina disciplina) {
        return disciplinaRepository.updateDisciplina(disciplina);
    }

    @GetMapping("/disciplina/{nombreDisciplina}")
    public Disciplina getDisciplina(@PathVariable("nombreDisciplina") String nombreDisciplina) {
        return disciplinaRepository.getDisciplinaByNombreDisciplina(nombreDisciplina);
    }

    @DeleteMapping("/disciplina/{nombreDisciplina}")
    public String deleteDisciplina(@PathVariable("nombreDisciplina") String nombreDisciplina) {
        return disciplinaRepository.deleteDisciplinaByNombreDisciplina(nombreDisciplina);
    }

    @GetMapping("/disciplina")
    public List<Disciplina> getDisciplinas() {
        return disciplinaRepository.getAllDisciplinas();
    }
}