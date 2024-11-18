package proyecto;


public interface IMedico {
    
    void asignarHorario(Cita cita);
    void consultarHorarioDisponible();
    void consultarAgenda();
    
    String getEspecialidad();
    
}
