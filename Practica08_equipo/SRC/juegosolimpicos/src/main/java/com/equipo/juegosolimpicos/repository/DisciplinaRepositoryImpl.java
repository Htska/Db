package com.equipo.juegosolimpicos.repository;

import com.equipo.juegosolimpicos.model.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> Clase que implementa las operaciones en el repositorio para Disciplina. </p>
 * @author equipo.
 */
@Repository
public class DisciplinaRepositoryImpl implements DisciplinaRepository {
    private static final String insertDisciplinaQuery = "insert into disciplina (nombreDisciplina, categoria) values (?, ?)";
    private static final String updateDisciplinaByNombreDisciplinaQuery = "update disciplina set  categoria=? where nombreDisciplina = ?";
    private static final String getDisciplinaByNombreDisciplinaQuery = "select * from disciplina where nombreDisciplina = ?";
    private static final String deleteDisciplinaByNombreDisciplinaQuery = "delete from disciplina where nombreDisciplina = ?";
    private static final String getAllDisciplinasQuery = "select * from disciplina";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    /** 
     * Inserta y regresa una disciplina en la base de datos.
     * @param disciplina La disciplina a insertar.
     * @return Disciplina La disciplina insertada.
     */
    @Override
    public Disciplina saveDisciplina(Disciplina disciplina) {
        jdbcTemplate.update(insertDisciplinaQuery, disciplina.getNombreDisciplina(), disciplina.getCategoria());
        return disciplina;
    }

    
    /** 
     * Actualiza y regresa los datos de una disciplina dada.
     * @param disciplina La disciplina con los datos a actualizar.
     * @return Disciplina La disciplina con los datos actualizados.
     */
    @Override
    public Disciplina updateDisciplina(Disciplina disciplina) {
        jdbcTemplate.update(updateDisciplinaByNombreDisciplinaQuery, disciplina.getCategoria(), disciplina.getNombreDisciplina());
        return disciplina;
    }

    
    /**
     * Selecciona y regresa la disciplina con el nombre dado. 
     * @param nombreDisciplina El nombre de la disciplina a buscar.
     * @return Disciplina Si existe, la disciplina con el nombre dado.
     *         Regresa null si dicha disciplina no existe.
     */
    @Override
    public Disciplina getDisciplinaByNombreDisciplina(String nombreDisciplina) {
        try {
            return jdbcTemplate.queryForObject(getDisciplinaByNombreDisciplinaQuery, BeanPropertyRowMapper.newInstance(Disciplina.class), nombreDisciplina);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    
    /** 
     * Elimina la disciplina con el nombre dado.
     * @param nombreDisciplina El nombre de la disciplina a eliminar.
     * @return String Mensaje que confirma la eliminación de la disciplina 
     *         con el nombre dado.
     */
    @Override
    public String deleteDisciplinaByNombreDisciplina(String nombreDisciplina) {
        jdbcTemplate.update(deleteDisciplinaByNombreDisciplinaQuery, nombreDisciplina);
        return "Disciplina " + nombreDisciplina + " eliminada";
    }

    
    /** 
     * Selecciona y regresa todas las disciplinas en la base de datos.
     * @return List<Disciplina> Todas las disciplinas que se tienen.
     */
    @Override
    public List<Disciplina> getAllDisciplinas() {
        return jdbcTemplate.query(getAllDisciplinasQuery, BeanPropertyRowMapper.newInstance(Disciplina.class));
    }
}
