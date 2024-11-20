package com.mycompany.citasapp;

public interface IPaciente {
    
    void consultarAgenda();
    void reservarCita(Cita cita);
    void cancelarCita(int idCita);
    
    String getApellido();
    void setTelefono(int telefono);
}
