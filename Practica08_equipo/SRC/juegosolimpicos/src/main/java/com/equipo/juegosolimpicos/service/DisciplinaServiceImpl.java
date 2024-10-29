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

    
    /** 
     * @param disciplina
     * @return Disciplina
     */
    @Override
    public Disciplina saveDisciplina(Disciplina disciplina) {
        return disciplinaRepository.saveDisciplina(disciplina);
    }

    
    /** 
     * @param disciplina
     * @return Disciplina
     */
    @Override
    public Disciplina updateDisciplina(Disciplina disciplina) {
        return disciplinaRepository.updateDisciplina(disciplina);
    }

    
    /** 
     * @param nombreDisciplina
     * @return Disciplina
     */
    @Override
    public Disciplina getDisciplinaByNombreDisciplina(String nombreDisciplina) {
        return disciplinaRepository.getDisciplinaByNombreDisciplina(nombreDisciplina);
    }

    
    /** 
     * @param nombreDisciplina
     * @return String
     */
    @Override
    public String deleteDisciplinaByNombreDisciplina(String nombreDisciplina) {
        return disciplinaRepository.deleteDisciplinaByNombreDisciplina(nombreDisciplina);
    }

    
    /** 
     * @return List<Disciplina>
     */
    @Override
    public List<Disciplina> getAllDisciplinas() {
        return disciplinaRepository.getAllDisciplinas();
    }
}
