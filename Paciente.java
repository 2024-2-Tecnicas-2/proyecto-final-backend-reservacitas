package com.mycompany.citasapp;

import com.mycompany.citasapp.Cita;
import java.util.ArrayList;
import java.util.List;

public class Paciente extends Usuario implements IPaciente {

    private int telefono;
    private int edad;
    private String apellido;
    private List<Cita> citasReservadas = new ArrayList<>();

    public Paciente(int telefono, int edad, String apellido, String nombre, int id) {
        super(nombre, id);
        this.telefono = telefono;
        this.edad = edad;
        this.apellido = apellido;
    }

    public void consultarAgenda() {
        if (citasReservadas.isEmpty()) {
            System.out.println("No tienes citas programadas.");
        } else {
            System.out.println("Agenda de citas:");
            for (Cita cita : citasReservadas) {
                System.out.println("ID: " + cita.getIdCita() + " | Fecha: " + cita.getFecha() + " | Hora: " + cita.getHora());
            }
        }
    }

    @Override
    public void reservarCita(Cita cita) {
        citasReservadas.add(cita);
        System.out.println("Cita reservada con exito para la fecha: " + cita.getFecha() + " a las " + cita.getHora());
    }

    @Override
    public void cancelarCita(int idCita) {
        Cita citaCancelar = null;
        for (Cita cita : citasReservadas) {
            if (cita.getIdCita() == idCita) {
                citaCancelar = cita;
                break;
            }
        }
        if (citaCancelar != null) {
            citasReservadas.remove(citaCancelar);
            System.out.println("Cita cancelada con exito.");
        } else {
            System.out.println("No se encontro una cita con el ID proporcionado.");
        }
    }

    @Override
    public String getApellido() {
        return apellido;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getTelefono() {
        return telefono;
    }

    public int getEdad() {
        return edad;
    }

    public List<Cita> getCitasReservadas() {
        return citasReservadas;
    }
}
