package com.mycompany.citasapp;

import java.util.Scanner;

public class Proyecto {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Medico medico = new Medico("Cardiología", 1, "Dr. Perez");
        Paciente paciente = new Paciente(123456789, 30, "Lopez", "Carlos", 2);


        Cita cita1 = new Cita(1, "20/11/2024", "10:00", medico, paciente);
        Cita cita2 = new Cita(2, "20/11/2024", "11:00", medico, paciente);
        cita1.confirmarCita();
        cita2.confirmarCita();
        medico.getHorarioDisponible().add(cita1);
        medico.getHorarioDisponible().add(cita2);
        paciente.reservarCita(cita1);
        paciente.reservarCita(cita2);

        int option;
        do {
            System.out.println("====================================");
            System.out.println("         MENU DE OPCIONES           ");
            System.out.println("====================================");
            System.out.println("1. Consultar horarios disponibles del medico");
            System.out.println("2. Asignar un horario al medico");
            System.out.println("3. Consultar agenda del medico");
            System.out.println("4. Consultar agenda del paciente");
            System.out.println("5. Reservar una cita para el paciente");
            System.out.println("6. Cancelar una cita del paciente");
            System.out.println("7. Confirmar una cita");
            System.out.println("8. Eliminar una cita");
            System.out.println("9. Ver citas confirmadas");
            System.out.println("10. Ver citas programadas");
            System.out.println("11. Salir");
            System.out.println("====================================");
            System.out.print("Seleccione una opcion: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    medico.consultarHorariosDisponibles();
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
                    System.out.println("Horario asignado al medico.");
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
                    boolean encontrada = false;
                    for (Cita c : medico.getHorarioDisponible()) {
                        if (c.getIdCita() == idCita) {
                            paciente.reservarCita(c);
                            medico.asignarHorario(c);
                            encontrada = true;
                            break;
                        }
                    }
                    if (!encontrada) {
                        System.out.println("Cita no encontrada en los horarios disponibles.");
                    }
                    break;
                case 6:
                    System.out.print("Ingrese ID de la cita a cancelar: ");
                    idCita = scanner.nextInt();
                    paciente.cancelarCita(idCita);
                    break;
                case 7:
                    System.out.print("Ingrese el ID de la cita a confirmar: ");
                    int idConfirmar = scanner.nextInt();
                    boolean citaConfirmada = false;
                    for (Cita c : paciente.getCitasReservadas()) {
                        if (c.getIdCita() == idConfirmar) {
                            c.confirmarCita();
                            citaConfirmada = true;
                            System.out.println("Cita confirmada.");
                            break;
                        }
                    }
                    if (!citaConfirmada) {
                        System.out.println("Cita no encontrada.");
                    }
                    break;
                case 8:
                    System.out.println("====== Eliminar Cita ======");
                    System.out.println("1. Eliminar cita programada");
                    System.out.println("2. Eliminar cita confirmada");
                    System.out.print("Seleccione una opción: ");
                    int subOption = scanner.nextInt();
                    eliminarCita(subOption, paciente, medico);
                    break;
                case 9:
                    System.out.println("===== VER CITAS CONFIRMADAS =====");
                    verCitasConfirmadas();
                    break;
                case 10:
                    System.out.println("===== VER CITAS PROGRAMADAS =====");
                    verCitasProgramadas();
                    break;
                case 11:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
            System.out.println();
        } while (option != 11);

        scanner.close();
    }

    private static void eliminarCita(int subOption, Paciente paciente, Medico medico) {
        Scanner scanner = new Scanner(System.in);
        if (subOption == 1) {
            System.out.print("Ingrese el ID de la cita programada a eliminar: ");
            int idEliminar = scanner.nextInt();
            boolean citaProgramadaEncontrada = false;
            for (Cita cita : paciente.getCitasReservadas()) {
                if (cita.getIdCita() == idEliminar && !cita.isConfirmada()) {
                    paciente.cancelarCita(idEliminar);
                    medico.getAgenda().removeIf(c -> c.getIdCita() == idEliminar);
                    System.out.println("Cita programada eliminada con éxito.");
                    citaProgramadaEncontrada = true;
                    break;
                }
            }
            if (!citaProgramadaEncontrada) {
                System.out.println("No se encontró una cita programada con ese ID.");
            }
        } else if (subOption == 2) {
            System.out.print("Ingrese el ID de la cita confirmada a eliminar: ");
            int idEliminarConfirmada = scanner.nextInt();
            boolean citaConfirmadaEncontrada = false;
            for (Cita cita : paciente.getCitasReservadas()) {
                if (cita.getIdCita() == idEliminarConfirmada && cita.isConfirmada()) {
                    paciente.cancelarCita(idEliminarConfirmada);
                    medico.getAgenda().removeIf(c -> c.getIdCita() == idEliminarConfirmada);
                    System.out.println("Cita confirmada eliminada con éxito.");
                    citaConfirmadaEncontrada = true;
                    break;
                }
            }
            if (!citaConfirmadaEncontrada) {
                System.out.println("No se encontró una cita confirmada con ese ID.");
            }
        }
    }

    private static void verCitasConfirmadas() {
      
        System.out.println("Estas son las citas confirmadas:");
    
    }

    private static void verCitasProgramadas() {
    
        System.out.println("Estas son las citas programadas:");
  
    }
}
