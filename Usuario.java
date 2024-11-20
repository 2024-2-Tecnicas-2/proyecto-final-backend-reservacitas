package com.mycompany.citasapp;

public abstract class Usuario {

    private String nombre;
    private int id;

    public Usuario(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id >= 0) {
            this.id = id;
        } else {
            System.out.println("ID no válido. El ID debe ser un número positivo.");
        }
    }

    public abstract void consultarAgenda();
}
