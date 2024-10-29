package com.equipo.juegosolimpicos.service;

import com.equipo.juegosolimpicos.model.Atleta;

import java.util.List;

/**
 * <p> Interfaz de los servicios para Atleta. </p>
 * @author equipo.
 */
public interface AtletaService {
    
    /** 
     * Inserta y regresa un atleta en la base de datos.
     * @param atleta El atleta que se quiere insertar.
     * @return Atleta El atleta que se insertó.
     */
    Atleta saveAtleta(Atleta atleta);
    
    /** 
     * Atualiza y regresa los datos del atleta en la base de datos.
     * @param atleta El atleta con los datos a actualizar.
     * @return Atleta El atleta que se actualizó.
     */
    Atleta updateAtleta(Atleta atleta);
    
    /** 
     * Regresa el atleta que tiene el número de pasaporte dado.
     * @param numeroPasaporte El número de pasaporte que tiene el atleta.
     * @return Atleta El atleta con el número de pasaporte dado.
     */
    Atleta getAtletaByNumeroPasaporte(String numeroPasaporte);
    
    /** 
     * Elimina el atleta con el número de pasaporte dado.
     * @param numeroPasaporte El número de pasaporte que tiene el atleta.
     * @return String Mensaje que confirma la eliminación.
     */
    String deleteAtletaByNumeroPasaporte(String numeroPasaporte);
    
    /** 
     * Se regresan todos los atletas en la base de datos.
     * @return List<Atleta> Todos los atletas que se tienen.
     */
    List<Atleta> getAllAtletas();
}
