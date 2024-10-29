package com.equipo.juegosolimpicos.model;

/**
 * <p> Clase para el modelo de Disciplina </p>
 * @author equipo.
 */
public class Disciplina {
    /**
     * El nombre de la discipplina
     */
    private String nombreDisciplina;

    /**
     * La categoría de la disciplina
     */
    private String categoria;

    /**
     * Constructor por omisión
     */
    public Disciplina() {
    }

    /**
     * Constructor de disciplina
     * @param nombreDisciplina El nombre de la disciplina
     * @param categoria La categoría de la disciplina
     */
    public Disciplina(String nombreDisciplina, String categoria) {
        this.nombreDisciplina = nombreDisciplina;
        this.categoria = categoria;
    }

    /**
     * Regresa el nombre de la disciplina
     * @return String el nombre de la disciplina
     */
    public String getNombreDisciplina() {
        return nombreDisciplina;
    }

    /**
     * Define un nuevo nombre a la disciplina
     * @param nombreDisciplina el nuevo nombre de la disciplina
     */
    public void setNombreDisciplina(String nombreDisciplina) {
        this.nombreDisciplina = nombreDisciplina;
    }

    /**
     * Regresa la categoría de la disciplina
     * @return String la categoría
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define una nueva categoría a la disciplina
     * @param categoria Regresa la categoría de la disciplina
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Representación de cadena de una disciplina
     * @return String la representación en cadena de la disciplina
     */
    @Override
    public String toString() {
        return "Disciplina{" +
                "nombreDisciplina='" + nombreDisciplina + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}