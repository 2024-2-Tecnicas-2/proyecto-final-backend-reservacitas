package com.mycompany.citasapp;

import java.util.ArrayList;
import java.util.List;

public class Medico extends Usuario implements IMedico {

    private String especialidad;
    private List<Cita> horarioDisponible = new ArrayList<>();
    private List<Cita> agenda = new ArrayList<>();

    public Medico(String especialidad, int id, String nombre) {
        super(nombre, id);
        this.especialidad = especialidad;
    }

    @Override
    public String getEspecialidad() {
        return especialidad;
    }

    public List<Cita> getHorarioDisponible() {
        return horarioDisponible;
    }

    public List<Cita> getAgenda() {
        return agenda;
    }

    @Override
    public void asignarHorario(Cita cita) {
        if (horarioDisponible.contains(cita)) {
            horarioDisponible.remove(cita);
            agenda.add(cita);
            System.out.println("Cita asignada correctamente.");
        } else {
            System.out.println("Horario no disponible.");
        }
    }

    public void consultarHorariosDisponibles() {
        if (horarioDisponible.isEmpty()) {
            System.out.println("No hay horarios disponibles.");
        } else {
            System.out.println("Horarios disponibles:");
            for (Cita cita : horarioDisponible) {
                System.out.println("ID: " + cita.getIdCita() + " | Fecha: " + cita.getFecha() + " | Hora: " + cita.getHora());
            }
        }
    }

    @Override
    public void consultarAgenda() {
        if (agenda.isEmpty()) {
            System.out.println("La agenda esta vacia.");
        } else {
            System.out.println("Agenda de citas:");
            for (Cita cita : agenda) {
                System.out.println("ID: " + cita.getIdCita() + " | Fecha: " + cita.getFecha() + " | Hora: " + cita.getHora());
            }
        }
    }

    @Override
    public void consultarHorarioDisponible() {
        consultarHorariosDisponibles();
    }
}
