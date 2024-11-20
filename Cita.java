package com.mycompany.citasapp;

public class Cita {

    static Iterable<Cita> getCitas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

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

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
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

    public boolean isConfirmada() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
