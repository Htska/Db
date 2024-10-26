package com.equipo.juegosolimpicos.model;

public class Disciplina {
    private String nombreDisciplina;
    private String categoria;

    public Disciplina() {
    }

    public Disciplina(String nombreDisciplina, String categoria) {
        this.nombreDisciplina = nombreDisciplina;
        this.categoria = categoria;
    }

    public String getNombreDisciplina() {
        return nombreDisciplina;
    }

    public void setNombreDisciplina(String nombreDisciplina) {
        this.nombreDisciplina = nombreDisciplina;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "nombreDisciplina='" + nombreDisciplina + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}