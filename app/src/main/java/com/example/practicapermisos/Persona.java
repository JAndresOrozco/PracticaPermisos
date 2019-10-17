package com.example.practicapermisos;

import java.io.Serializable;

public class Persona implements Serializable {
    private String Nombre;
    private String Edad;

    public Persona(String nombre, String edad) {
        Nombre = nombre;
        Edad = edad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }
}
