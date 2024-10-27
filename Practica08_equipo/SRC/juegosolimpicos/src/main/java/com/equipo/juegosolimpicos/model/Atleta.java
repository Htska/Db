package com.equipo.juegosolimpicos.model;

import java.time.LocalDate;

public class Atleta {
    private String numeroPasaporte;
    private String nombrePais;
    private LocalDate fechaNacimiento;
    private String nacionalidad;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private char genero;

    public Atleta() {
    }

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

    public String getNumeroPasaporte() {
        return numeroPasaporte;
    }

    public void setNumeroPasaporte(String numeroPasaporte) {
        this.numeroPasaporte = numeroPasaporte;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
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
