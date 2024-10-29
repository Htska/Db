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

    /**
     * Método para añadir una disciplina en donde se crea la ruta /disciplina por
     * medio de @PostMapping el cuál escucha las peticiones POST.
     * 
     * @param disciplina objeto de tipo Disciplina el cual guardaremos en la base de
     *                   datos.
     * @return el objeto de tipo Disciplina guardado en la base de datos.
     */
    @PostMapping("/disciplina")
    public Disciplina addDisciplina(@RequestBody Disciplina disciplina) {
        return disciplinaRepository.saveDisciplina(disciplina);
    }

    /**
     * Método para actualizar una disciplina en donde se crea la ruta /disciplina
     * por medio de @PutMapping el cuál escucha las peticiones PUT.
     * 
     * @param disciplina objeto de tipo Disciplina el cual actualizaremos en la base
     *                   de datos.
     * @return el objeto de tipo Disciplina actualizado en la base de datos.
     */
    @PutMapping("/disciplina")
    public Disciplina updateDisciplina(@RequestBody Disciplina disciplina) {
        return disciplinaRepository.updateDisciplina(disciplina);
    }

    /**
     * Método para obtener una disciplina en donde se crea la ruta
     * /disciplina/{nombreDisciplina} por medio de @GetMapping el cuál escucha las
     * peticiones GET.
     * 
     * @param nombreDisciplina nombre de la disciplina que se desea obtener. (se
     *                         utilizará como parte de PathVariable)
     * @return el objeto de tipo Disciplina que se obtuvo de la base de datos.
     */
    @GetMapping("/disciplina/{nombreDisciplina}")
    public Disciplina getDisciplina(@PathVariable("nombreDisciplina") String nombreDisciplina) {
        return disciplinaRepository.getDisciplinaByNombreDisciplina(nombreDisciplina);
    }

    /**
     * Método para eliminar una disciplina en donde se crea la ruta
     * /disciplina/{nombreDisciplina} por medio de @DeleteMapping el cuál escucha
     * las peticiones DELETE.
     * 
     * @param nombreDisciplina nombre de la disciplina que se desea eliminar. (se
     *                         utilizará como parte de PathVariable)
     * @return Mensaje del estado de la eliminación de la disciplina.
     */
    @DeleteMapping("/disciplina/{nombreDisciplina}")
    public String deleteDisciplina(@PathVariable("nombreDisciplina") String nombreDisciplina) {
        return disciplinaRepository.deleteDisciplinaByNombreDisciplina(nombreDisciplina);
    }

    /**
     * Método para obtener todas las disciplinas en donde se crea la ruta
     * /disciplina por medio de @GetMapping el cuál escucha las peticiones GET.
     * 
     * @return la lista de objetos de tipo Disciplina que se obtuvo de la base de
     *         datos.
     */
    @GetMapping("/disciplina")
    public List<Disciplina> getDisciplinas() {
        return disciplinaRepository.getAllDisciplinas();
    }
}