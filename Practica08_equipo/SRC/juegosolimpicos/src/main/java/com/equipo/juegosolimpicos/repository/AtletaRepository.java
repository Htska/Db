package com.equipo.juegosolimpicos.repository;

import com.equipo.juegosolimpicos.model.Atleta;

import java.util.List;

/**
 * <p> Interfaz con las operaciones en el repositorio para Atleta. </p>
 * @author equipo.
 */
public interface AtletaRepository {
    /** 
     * Inserta un atleta en la base de datos.
     * @param atleta El atleta a insertar.
     * @return Atleta El atleta insertado.
     */
    Atleta saveAtleta(Atleta atleta);
    
    /** 
     * Actualiza los datos de un atleta dado.
     * @param atleta El atleta con los datos a actualizar.
     * @return Atleta El atleta con los datos actualizados.
     */
    Atleta updateAtleta(Atleta atleta);
    
    /** 
     * Selecciona el atleta con el número de pasaporte dado.
     * @param numeroPasaporte El número de pasaporte que tiene el atleta.
     * @return Atleta El atleta con el número de pasaporte dado.
     */
    Atleta getAtletaByNumeroPasaporte(String numeroPasaporte);
    
    /** 
     * Elimina el atleta con el número de pasaporte dado.
     * @param numeroPasaporte El número de pasaporte que tiene el atleta.
     * @return String Mensaje que confirma la eliminación del atleta.
     */
    String deleteAtletaByNumeroPasaporte(String numeroPasaporte);
    
    /** 
     * Selecciona todos los atletas en la base de datos.
     * @return List<Atleta> Todos los atletas que se tienen.
     */
    List<Atleta> getAllAtletas();
}
