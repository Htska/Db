package com.equipo.juegosolimpicos.repository;

import com.equipo.juegosolimpicos.model.Atleta;

import java.util.List;

public interface AtletaRepository {
    Atleta saveAtleta(Atleta atleta);
    Atleta updateAtleta(Atleta atleta);
    Atleta getAtletaByNumeroPasaporte(String numeroPasaporte);
    String deleteAtletaByNumeroPasaporte(String numeroPasaporte);
    List<Atleta> getAllAtletas();
}
