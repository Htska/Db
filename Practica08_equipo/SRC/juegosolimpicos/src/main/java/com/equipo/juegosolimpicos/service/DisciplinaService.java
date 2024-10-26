package com.equipo.juegosolimpicos.service;

import com.equipo.juegosolimpicos.model.Disciplina;

import java.util.List;

public interface DisciplinaService {
    Disciplina saveDisciplina(Disciplina disciplina);
    Disciplina updateDisciplina(Disciplina disciplina);
    Disciplina getDisciplinaByNombreDisciplina(String nombreDisciplina);
    String deleteDisciplinaByNombreDisciplina(String nombreDisciplina);
    List<Disciplina> getAllDisciplinas();
}
