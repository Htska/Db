package com.equipo.juegosolimpicos.service;

import com.equipo.juegosolimpicos.model.Disciplina;
import com.equipo.juegosolimpicos.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Override
    public Disciplina saveDisciplina(Disciplina disciplina) {
        return disciplinaRepository.saveDisciplina(disciplina);
    }

    @Override
    public Disciplina updateDisciplina(Disciplina disciplina) {
        return disciplinaRepository.updateDisciplina(disciplina);
    }

    @Override
    public Disciplina getDisciplinaByNombreDisciplina(String nombreDisciplina) {
        return disciplinaRepository.getDisciplinaByNombreDisciplina(nombreDisciplina);
    }

    @Override
    public String deleteDisciplinaByNombreDisciplina(String nombreDisciplina) {
        return disciplinaRepository.deleteDisciplinaByNombreDisciplina(nombreDisciplina);
    }

    @Override
    public List<Disciplina> getAllDisciplinas() {
        return disciplinaRepository.getAllDisciplinas();
    }
}
