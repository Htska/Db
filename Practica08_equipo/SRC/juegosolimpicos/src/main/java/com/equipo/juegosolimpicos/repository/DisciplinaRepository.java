package com.equipo.juegosolimpicos.repository;

import com.equipo.juegosolimpicos.model.Disciplina;

import java.util.List;

/**
 * <p> Interfaz con las operaciones en el repositorio para Disciplina. </p>
 * @author equipo.
 */
public interface DisciplinaRepository {
    /** 
     * Inserta y regresa una disciplina en la base de datos.
     * @param disciplina La disciplina a insertar.
     * @return Disciplina La disciplina insertada.
     */
    Disciplina saveDisciplina(Disciplina disciplina);

    /** 
     * Actualiza y regresa los datos de una disciplina dada.
     * @param disciplina La disciplina con los datos a actualizar.
     * @return Disciplina La disciplina con los datos actualizados.
     */
    Disciplina updateDisciplina(Disciplina disciplina);
    
    /**
     * Selecciona y regresa la disciplina con el nombre dado. 
     * @param nombreDisciplina El nombre de la disciplina a buscar.
     * @return Disciplina La disciplina con el nombre dado.
     */
    Disciplina getDisciplinaByNombreDisciplina(String nombreDisciplina);
    
    /** 
     * Elimina la disciplina con el nombre dado.
     * @param nombreDisciplina El nombre de la disciplina a eliminar.
     * @return String Mensaje que confirma la eliminación de la disciplina.
     */
    String deleteDisciplinaByNombreDisciplina(String nombreDisciplina);
    
    /** 
     * Selecciona y regresa todas las disciplinas en la base de datos.
     * @return List<Disciplina> Todas las disciplinas que se tienen.
     */
    List<Disciplina> getAllDisciplinas();
}
