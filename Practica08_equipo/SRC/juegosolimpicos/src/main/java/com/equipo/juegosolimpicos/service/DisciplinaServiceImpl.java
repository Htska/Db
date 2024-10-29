package com.equipo.juegosolimpicos.service;

import com.equipo.juegosolimpicos.model.Disciplina;
import com.equipo.juegosolimpicos.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> Clase que implementa los servicios para Disciplina. </p>
 * @author equipo.
 */
@Service
public class DisciplinaServiceImpl implements DisciplinaService {
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    
    /** 
     * Inserta y regresa una disciplina en la base de datos.
     * @param disciplina La disciplina a insertar.
     * @return Disciplina La disciplina insertada.
     */
    @Override
    public Disciplina saveDisciplina(Disciplina disciplina) {
        return disciplinaRepository.saveDisciplina(disciplina);
    }

    
    /** 
     * Actualiza y regresa los datos de una disciplina dada.
     * @param disciplina La disciplina con los datos a actualizar.
     * @return Disciplina La disciplina con los datos actualizados.
     */
    @Override
    public Disciplina updateDisciplina(Disciplina disciplina) {
        return disciplinaRepository.updateDisciplina(disciplina);
    }

    
    /**
     * Regresa la disciplina con el nombre dado. 
     * @param nombreDisciplina El nombre de la disciplina a buscar.
     * @return Disciplina Si existe, la disciplina con el nombre dado.
     *         Regresa null si dicha disciplina no existe.
     */
    @Override
    public Disciplina getDisciplinaByNombreDisciplina(String nombreDisciplina) {
        return disciplinaRepository.getDisciplinaByNombreDisciplina(nombreDisciplina);
    }

    
    /** 
     * Elimina la disciplina con el nombre dado.
     * @param nombreDisciplina El nombre de la disciplina a eliminar.
     * @return String Mensaje que confirma la eliminación de la disciplina 
     *         con el nombre dado.
     */
    @Override
    public String deleteDisciplinaByNombreDisciplina(String nombreDisciplina) {
        return disciplinaRepository.deleteDisciplinaByNombreDisciplina(nombreDisciplina);
    }

    
    /** 
     * Regresa todas las disciplinas en la base de datos.
     * @return List<Disciplina> Todas las disciplinas que se tienen.
     */
    @Override
    public List<Disciplina> getAllDisciplinas() {
        return disciplinaRepository.getAllDisciplinas();
    }
}
