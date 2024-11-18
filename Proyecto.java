package proyecto;
import java.util.Scanner;

public class Proyecto {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Medico medico = new Medico("Cardiología", "Dr. Pérez", 1);
        Paciente paciente = new Paciente(123456789, 30, "López", "Carlos", 2);
        
        int option;
        do {
            System.out.println("Menú de opciones:");
            System.out.println("1. Consultar horarios disponibles del médico");
            System.out.println("2. Asignar un horario al médico");  
            System.out.println("3. Consultar agenda del médico");
            System.out.println("4. Consultar agenda del paciente");
            System.out.println("5. Reservar una cita para el paciente");
            System.out.println("6. Cancelar una cita del paciente");
            System.out.println("7. Confirmar una cita");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            
            switch (option) {
                case 1:
                    medico.consultarHorarioDisponible();
                    break;
                case 2:
                    System.out.print("Ingrese ID de la cita: ");
                    int idCita = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese fecha (DD/MM/YYYY): ");
                    String fecha = scanner.nextLine();
                    System.out.print("Ingrese hora (HH:MM): ");
                    String hora = scanner.nextLine();
                    Cita cita = new Cita(idCita, fecha, hora, medico, paciente);
                    medico.getHorarioDisponible().add(cita);
                    System.out.println("Horario asignado al médico.");
                    break;
                case 3:
                    medico.consultarAgenda();
                    break;
                case 4:
                    paciente.consultarAgenda();
                    break;
                case 5:
                    System.out.print("Ingrese ID de la cita a reservar: ");
                    idCita = scanner.nextInt();
                    for (Cita c : medico.getHorarioDisponible()) {
                        if (c.getIdCita() == idCita) {
                            paciente.reservarCita(c);
                            medico.asignarHorario(c);
                            break;
                        }
                    }
                    break;
                case 6:
                    System.out.print("Ingrese ID de la cita a cancelar: ");
                    idCita = scanner.nextInt();
                    paciente.cancelarCita(idCita);
                    break;
                case 7:
                    System.out.print("Ingrese ID de la cita a confirmar: ");
                    idCita = scanner.nextInt();
                    for (Cita c : paciente.getCitaDisponible()) {
                        if (c.getIdCita() == idCita) {
                            c.confirmarCita();
                            break;
                        }
                    }
                    break;
                case 8:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
            System.out.println();
        } while (option != 8);
        
        scanner.close();
    }
}

