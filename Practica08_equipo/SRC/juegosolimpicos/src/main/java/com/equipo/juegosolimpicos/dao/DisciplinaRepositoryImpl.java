package com.equipo.juegosolimpicos.dao;

import com.equipo.juegosolimpicos.entity.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DisciplinaRepositoryImpl implements DisciplinaRepository {
    private static final String insertDisciplinaQuery = "insert into disciplina (nombreDisciplina, categoria) values (?, ?)";
    private static final String updateDisciplinaByNombreDisciplinaQuery = "update disciplina set categoria=? where nombreDisciplina = ?";
    private static final String getDisciplinaByNombreDisciplinaQuery = "select * from disciplina where nombreDisciplina = ?";
    private static final String deleteDisciplinaByNombreDisciplinaQuery = "delete from disciplina where nombreDisciplina = ?";
    private static final String getAllDisciplinasQuery = "select * from disciplina";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Disciplina saveDisciplina(Disciplina disciplina) {
        jdbcTemplate.update(insertDisciplinaQuery, disciplina.getNombreDisciplina(), disciplina.getCategoria());
        return disciplina;
    }

    @Override
    public Disciplina updateDisciplina(Disciplina disciplina) {
        jdbcTemplate.update(updateDisciplinaByNombreDisciplinaQuery, disciplina.getCategoria(), disciplina.getNombreDisciplina());
        return disciplina;
    }

    @Override
    public Disciplina getDisciplinaByNombreDisciplina(String nombreDisciplina) {
        try {
            return jdbcTemplate.queryForObject(getDisciplinaByNombreDisciplinaQuery, BeanPropertyRowMapper.newInstance(Disciplina.class), nombreDisciplina);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public String deleteDisciplinaByNombreDisciplina(String nombreDisciplina) {
        jdbcTemplate.update(deleteDisciplinaByNombreDisciplinaQuery, nombreDisciplina);
        return "Disciplina " + nombreDisciplina + " eliminada";
    }

    @Override
    public List<Disciplina> getAllDisciplinas() {
        return jdbcTemplate.query(getAllDisciplinasQuery, BeanPropertyRowMapper.newInstance(Disciplina.class));
    }
}
