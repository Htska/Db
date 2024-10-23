package com.equipo.juegosolimpicos.dao;

import com.equipo.juegosolimpicos.entity.Disciplina;

import java.util.List;

public interface DisciplinaRepository {
    Disciplina saveDisciplina(Disciplina disciplina);
    Disciplina updateDisciplina(Disciplina disciplina);
    Disciplina getDisciplinaByNombreDisciplina(String nombreDisciplina);
    String deleteDisciplinaByNombreDisciplina(String nombreDisciplina);
    List<Disciplina> getAllDisciplinas();
}
