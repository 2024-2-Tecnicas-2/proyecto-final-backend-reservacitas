package proyecto;

public class Cita {

    private int idCita;
    private String fecha;
    private String hora;
    private Medico medico;
    private Paciente paciente;

    public Cita(int idCita, String fecha, String hora, Medico medico, Paciente paciente) {
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.medico = medico;
        this.paciente = paciente;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public void confirmarCita() {
        System.out.println("La cita ha sido confirmada para la fecha: " + fecha + " a las " + hora);
    }

    public void editarCita(String nuevaFecha, String nuevaHora) {
        this.fecha = nuevaFecha;
        this.hora = nuevaHora;
        System.out.println("La cita ha sido reprogramada para la fecha: " + nuevaFecha + " a las " + nuevaHora);
    }

    public void cancelarCita() {
        System.out.println("La cita ha sido cancelada.");
    }

    public void consultarDisponibilidad() {
        System.out.println("La disponibilidad de la cita es para la fecha: " + fecha + " a las " + hora);
    }

  
}
