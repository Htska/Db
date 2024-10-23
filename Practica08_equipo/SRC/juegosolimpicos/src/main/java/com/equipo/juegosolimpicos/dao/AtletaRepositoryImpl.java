package com.equipo.juegosolimpicos.dao;

import com.equipo.juegosolimpicos.entity.Atleta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AtletaRepositoryImpl implements AtletaRepository {
    private static final String insertAtletaQuery = "insert into atleta (numeroPasaporte, nombrePais, fechaNacimiento, nacionalidad, nombre, primerApellido, segundoApellido, genero) values (?, ?, ?, ?, ?, ?, ?, ?) ";
    private static final String updateAtletaByNumeroPasaporteQuery = "update atleta set nombrePais=?, fechaNacimiento=?, nacionalidad=?, nombre=?, primerApellido=?, segundoApellido=?, genero=? where numeroPasaporte=?";
    private static final String getAtletaByNumeroPasaporteQuery = "select * from atleta where numeroPasaporte=?";
    private static final String deleteAtletaByNumeroPasaporteQuery = "delete from atleta where numeroPasaporte=?";
    private static final String getAllAtletasQuery = "select * from atleta";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Atleta saveAtleta(Atleta atleta) {
        jdbcTemplate.update(insertAtletaQuery, atleta.getNumeroPasaporte(), atleta.getNombrePais(), atleta.getFechaNacimiento(), atleta.getNacionalidad(), atleta.getNombre(), atleta.getPrimerApellido(), atleta.getSegundoApellido(), atleta.getGenero());
        return atleta;
    }

    @Override
    public Atleta updateAtleta(Atleta atleta) {
        jdbcTemplate.update(updateAtletaByNumeroPasaporteQuery, atleta.getNombrePais(), atleta.getFechaNacimiento(), atleta.getNacionalidad(), atleta.getNombre(), atleta.getPrimerApellido(), atleta.getSegundoApellido(), atleta.getGenero(), atleta.getNumeroPasaporte());
        return atleta;
    }

    @Override
    public Atleta getAtletaByNumeroPasaporte(String numeroPasaporte) {
        try {
            return jdbcTemplate.queryForObject(getAtletaByNumeroPasaporteQuery, BeanPropertyRowMapper.newInstance(Atleta.class), numeroPasaporte);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public String deleteAtletaByNumeroPasaporte(String numeroPasaporte) {
        jdbcTemplate.update(deleteAtletaByNumeroPasaporteQuery, numeroPasaporte);
        return "Atleta con numero de pasaporte " + numeroPasaporte + " eliminado";
    }

    @Override
    public List<Atleta> getAllAtletas() {
        return jdbcTemplate.query(getAllAtletasQuery, BeanPropertyRowMapper.newInstance(Atleta.class));
    }
}
