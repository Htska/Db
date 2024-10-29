package com.equipo.juegosolimpicos.repository;

import com.equipo.juegosolimpicos.model.Atleta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> Clase que implementa las operaciones en el repositorio para Atleta. </p>
 * @author equipo.
 */
@Repository
public class AtletaRepositoryImpl implements AtletaRepository {
    private static final String insertAtletaQuery = "insert into atleta (numeroPasaporte, nombrePais, fechaNacimiento, nacionalidad, nombre, primerApellido, segundoApellido, genero) values (?, ?, ?, ?, ?, ?, ?, ?) ";
    private static final String updateAtletaByNumeroPasaporteQuery = "update atleta set nombrePais=?, fechaNacimiento=?, nacionalidad=?, nombre=?, primerApellido=?, segundoApellido=?, genero=? where numeroPasaporte=?";
    private static final String getAtletaByNumeroPasaporteQuery = "select * from atleta where numeroPasaporte=?";
    private static final String deleteAtletaByNumeroPasaporteQuery = "delete from atleta where numeroPasaporte=?";
    private static final String getAllAtletasQuery = "select * from atleta";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    /** 
     * Inserta un atleta en la base de datos.
     * @param atleta El atleta a insertar.
     * @return Atleta El atleta insertado.
     */
    @Override
    public Atleta saveAtleta(Atleta atleta) {
        jdbcTemplate.update(insertAtletaQuery, atleta.getNumeroPasaporte(), atleta.getNombrePais(), atleta.getFechaNacimiento(), atleta.getNacionalidad(), atleta.getNombre(), atleta.getPrimerApellido(), atleta.getSegundoApellido(), atleta.getGenero());
        return atleta;
    }

    
    /** 
     * Actualiza los datos de un atleta dado.
     * @param atleta El atleta con los datos a actualizar.
     * @return Atleta El atleta con los datos actualizados.
     */
    @Override
    public Atleta updateAtleta(Atleta atleta) {
        jdbcTemplate.update(updateAtletaByNumeroPasaporteQuery, atleta.getNombrePais(), atleta.getFechaNacimiento(), atleta.getNacionalidad(), atleta.getNombre(), atleta.getPrimerApellido(), atleta.getSegundoApellido(), atleta.getGenero(), atleta.getNumeroPasaporte());
        return atleta;
    }

    
    /** 
     * Selecciona el atleta con el número de pasaporte dado.
     * @param numeroPasaporte El número de pasaporte que tiene el atleta.
     * @return Atleta Si existe, el atleta con el número de pasaporte dado.
     *         Null si dicho atleta no existe.
     */
    @Override
    public Atleta getAtletaByNumeroPasaporte(String numeroPasaporte) {
        try {
            return jdbcTemplate.queryForObject(getAtletaByNumeroPasaporteQuery, BeanPropertyRowMapper.newInstance(Atleta.class), numeroPasaporte);
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    
    /** 
     * Elimina el atleta con el número de pasaporte dado.
     * @param numeroPasaporte El número de pasaporte que tiene el atleta.
     * @return String Mensaje que confirma la eliminación.
     */
    @Override
    public String deleteAtletaByNumeroPasaporte(String numeroPasaporte) {
        jdbcTemplate.update(deleteAtletaByNumeroPasaporteQuery, numeroPasaporte);
        return "Atleta con numero de pasaporte " + numeroPasaporte + " eliminado";
    }

    
    /** 
     * Selecciona todos los atletas en la base de datos.
     * @return List<Atleta> Todos los atletas que se tienen.
     */
    @Override
    public List<Atleta> getAllAtletas() {
        return jdbcTemplate.query(getAllAtletasQuery, BeanPropertyRowMapper.newInstance(Atleta.class));
    }
}
