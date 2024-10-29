package com.equipo.juegosolimpicos.service;

import com.equipo.juegosolimpicos.model.Atleta;
import com.equipo.juegosolimpicos.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> Clase que implementa los servicios para Atleta. </p>
 * @author equipo.
 */
@Service
public class AtletaServiceImpl implements AtletaService {
    @Autowired
    AtletaRepository atletaRepository;

    
    /** 
     * Inserta y regresa un atleta en la base de datos.
     * @param atleta El atleta que se quiere insertar.
     * @return Atleta El atleta que se insertó.
     */
    @Override
    public Atleta saveAtleta(Atleta atleta) {
        return atletaRepository.saveAtleta(atleta);
    }

    
    /** 
     * Atualiza y regresa los datos del atleta en la base de datos.
     * @param atleta El atleta con los datos a actualizar.
     * @return Atleta El atleta que se actualizó.
     */
    @Override
    public Atleta updateAtleta(Atleta atleta) {
        return atletaRepository.updateAtleta(atleta);
    }

    
    /** 
     * Regresa el atleta que tiene el número de pasaporte dado.
     * @param numeroPasaporte El número de pasaporte que tiene el atleta.
     * @return Atleta Si existe, el atleta con el número de pasaporte dado.
     *         Null si dicho atleta no existe.
     */
    @Override
    public Atleta getAtletaByNumeroPasaporte(String numeroPasaporte) {
        return atletaRepository.getAtletaByNumeroPasaporte(numeroPasaporte);
    }

    
    /** 
     * Elimina el atleta con el número de pasaporte dado.
     * @param numeroPasaporte El número de pasaporte que tiene el atleta.
     * @return String Mensaje que confirma la eliminación.
     */
    @Override
    public String deleteAtletaByNumeroPasaporte(String numeroPasaporte) {
        return atletaRepository.deleteAtletaByNumeroPasaporte(numeroPasaporte);
    }

    
    /** 
     * Se regresan todos los atletas en la base de datos.
     * @return List<Atleta> Todos los atletas que se tienen.
     */
    @Override
    public List<Atleta> getAllAtletas() {
        return atletaRepository.getAllAtletas();
    }
}
