package com.equipo.juegosolimpicos.model;

import java.time.LocalDate;
/**
 * <p> Clase para el modelo de atleta </p>
 * @author equipo.
 */
public class Atleta {
    /**
     * El número de pasaporte del atleta 
     */
    private String numeroPasaporte;

    /**
     * Nombre del país con el que compite el atleta.
     */
    private String nombrePais;

    /**
     * La fecha de nacimiento del atleta
     */
    private LocalDate fechaNacimiento;

    /**
     * La nacionalidad del atleta
     */
    private String nacionalidad;

    /**
     * Nombre del atleta
     */
    private String nombre;

    /**
     * El primer apellido del atleta
     */
    private String primerApellido;

    /**
     * El segundo apellido del atleta
     */
    private String segundoApellido;

    /**
     * El género del atleta
     */
    private char genero;

    /**
     * Constructor por omisión del atleta
     */
    public Atleta() {
    }

    /**
     * Constructor del atleta
     * @param numeroPasaporte el número de pasaporte
     * @param nombrePais el nombre del país
     * @param fechaNacimiento la fecha de nacimiento
     * @param nacionalidad la nacionalidad
     * @param nombre el nombre
     * @param primerApellido el primer apellido
     * @param segundoApellido el segundo apellido del atleta
     * @param genero el género del atleta
     */
    public Atleta(String numeroPasaporte,
            String nombrePais,
            LocalDate fechaNacimiento,
            String nacionalidad,
            String nombre,
            String primerApellido,
            String segundoApellido,
            char genero) {
        this.numeroPasaporte = numeroPasaporte;
        this.nombrePais = nombrePais;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.genero = genero;
    }

    /**
     * Regresa el número de pasaporte
     * @return String el número de pasaporte 
     */
    public String getNumeroPasaporte() {
        return numeroPasaporte;
    }

    /**
     * Define el número de pasaporte a un atleta
     * @param numeroPasaporte el nuevo número de pasaporte
     */
    public void setNumeroPasaporte(String numeroPasaporte) {
        this.numeroPasaporte = numeroPasaporte;
    }

    /**
     * Regresa el nombre del país por el que participa el atleta
     * @return String el nombre del país
     */
    public String getNombrePais() {
        return nombrePais;
    }

    /**
     * DEfine el nombre de un país al atleta
     * @param nombrePais el nuevo nombre del país
     */
    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    /**
     * Regresa la fecha de nacimiento del atleta
     * @return LocalDate la fecha de nacimiento
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * DEfine una nueva fecha de nacimiento al atleta
     * @param fechaNacimiento la nueva fecha de nacimiento
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Regresa la nacionalidad del atleta
     * @return String la nacionalidad del atleta
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Define una nueva nacionalidad al atleta
     * @param nacionalidad la nueva nacionalidad
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Regresa el nombre del atleta
     * @return String el nombre del atleta
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define un nombre al atleta
     * @param nombre el nuevo nombre del atleta
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Rehresa el primer apellido del atleta
     * @return String el primer apellido del atleta
     */
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     * define el primer apellido al atleta
     * @param primerApellido el nuevo primer apellido
     */
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * Regresa el segundo apellido del atleta
     * @return String el segundo apellido
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * Define el segundo apellido a un atleta
     * @param segundoApellido el segundo apellido del atleta
     */
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * Regresa el génerp del atleta
     * @return char el género del atleta
     */
    public char getGenero() {
        return genero;
    }

    /**
     * Define el género del atleta
     * @param genero el nuevo género del atleta
     */
    public void setGenero(char genero) {
        this.genero = genero;
    }

    /**
     * Regresa la representación en cadena del atleta
     * @return String la representación en cadena
     */
    @Override public String toString() {
                return "Atleta{" +
                "numeroPasaporte='" + numeroPasaporte + '\'' +
                ", nombrePais='" + nombrePais + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", nombre='" + nombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", genero=" + genero +
                '}';
    }
}
