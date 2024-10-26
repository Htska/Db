package com.equipo.juegosolimpicos.service;

import com.equipo.juegosolimpicos.model.Atleta;
import com.equipo.juegosolimpicos.repository.AtletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtletaServiceImpl implements AtletaService {
    @Autowired
    AtletaRepository atletaRepository;

    @Override
    public Atleta saveAtleta(Atleta atleta) {
        return atletaRepository.saveAtleta(atleta);
    }

    @Override
    public Atleta updateAtleta(Atleta atleta) {
        return atletaRepository.updateAtleta(atleta);
    }

    @Override
    public Atleta getAtletaByNumeroPasaporte(String numeroPasaporte) {
        return atletaRepository.getAtletaByNumeroPasaporte(numeroPasaporte);
    }

    @Override
    public String deleteAtletaByNumeroPasaporte(String numeroPasaporte) {
        return atletaRepository.deleteAtletaByNumeroPasaporte(numeroPasaporte);
    }

    @Override
    public List<Atleta> getAllAtletas() {
        return atletaRepository.getAllAtletas();
    }
}
