package proyecto;

import java.util.List;
import java.util.ArrayList;

public class Medico extends Usuario implements IMedico {

    private String especialidad;
    private List<Cita> horarioDisponible = new ArrayList<>();
    private List<Cita> agenda = new ArrayList<>();

    public Medico(String especialidad, String nombre, int id) {
        super(nombre, id);
        this.especialidad = especialidad;
    }

    @Override
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Cita> getHorarioDisponible() {
        return horarioDisponible;
    }

    public void setHorarioDisponible(List<Cita> horarioDisponible) {
        this.horarioDisponible = horarioDisponible;
    }

    public List<Cita> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Cita> agenda) {
        this.agenda = agenda;
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

    @Override
    public void consultarHorarioDisponible() {
        if (horarioDisponible.isEmpty()) {
            System.out.println("No hay horarios disponibles.");
        } else {
            System.out.println("Horarios disponibles:");
            for (Cita cita : horarioDisponible) {
                System.out.println(cita.getFecha()+" " + cita.getHora() +" " + cita.getMedico().getNombre());
            }
        }
    }

    @Override
    public void consultarAgenda() {
        if (agenda.isEmpty()) {
            System.out.println("La agenda esta vacia");
        } else {
            System.out.println("la agenda es:");
            for (Cita cita : agenda) {
                System.out.println(cita.getFecha()+" " + cita.getHora() +" " + cita.getMedico().getNombre());
            }

        }

    }
}
