package proyecto;

import java.util.ArrayList;
import java.util.List;

public class Paciente extends Usuario implements IPaciente {

    private int telefono;
    private int edad;
    private String apellido;
    private List<Cita> citaDisponible = new ArrayList<>();

    public Paciente(int telefono, int edad, String apellido, String nombre, int id) {
        super(nombre, id);
        this.telefono = telefono;
        this.edad = edad;
        this.apellido = apellido;
    }

    @Override
    public void consultarAgenda() {
        if (citaDisponible.isEmpty()) {
            System.out.println("No tienes citas programadas.");
        } else {
            System.out.println("Agenda de citas:");
            for (Cita cita : citaDisponible) {
                System.out.println("Cita ID: " + cita.getIdCita() + " | Fecha: " + cita.getFecha() + " | Hora: " + cita.getHora());
            }
        }
    }

    
    @Override
    public void reservarCita(Cita cita) {
        citaDisponible.add(cita);
        System.out.println("Cita reservada con éxito para la fecha: " + cita.getFecha() + " a las " + cita.getHora());
    }

    
    @Override
    public void cancelarCita(int idCita) {
        Cita citaCancelar = null;
        for (Cita cita : citaDisponible) {
            if (cita.getIdCita() == idCita) {
                citaCancelar = cita;
                break;
            }
        }
        if (citaCancelar != null) {
            citaDisponible.remove(citaCancelar);
            System.out.println("Cita cancelada con éxito.");
        } else {
            System.out.println("No se encontró una cita con el ID proporcionado.");
        }
    }

    @Override
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    @Override
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<Cita> getCitaDisponible() {
        return citaDisponible;
    }

    public void setCitaDisponible(List<Cita> citaDisponible) {
        this.citaDisponible = citaDisponible;
    }
}
