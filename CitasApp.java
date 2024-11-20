package com.mycompany.citasapp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CitasApp extends JFrame {
    private JTextField txtIdCita, txtFecha, txtHora, txtEspecialidadMedico, txtNombreMedico, txtNombrePaciente, txtIdCitaEliminar;
    private JTextArea txtAreaCitasProgramadas, txtAreaCitasConfirmadas;
    private List<Cita> citasProgramadas = new ArrayList<>();
    private List<Cita> citasConfirmadas = new ArrayList<>();
    private Medico medico;
    private Paciente paciente;

    public CitasApp() {
        
    }

    private void programarCita() {
        try {
            int idCita = Integer.parseInt(txtIdCita.getText());
            String fecha = txtFecha.getText();
            String hora = txtHora.getText();

            Cita cita = new Cita(idCita, fecha, hora, medico, paciente);
            citasProgramadas.add(cita);
            medico.getHorarioDisponible().add(cita);
            JOptionPane.showMessageDialog(this, "Cita programada con éxito.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al programar la cita. Verifique los datos.");
        }
    }

    private void confirmarCita() {
        String input = JOptionPane.showInputDialog(this, "Ingrese el ID de la cita que desea confirmar:", 
                                                   "Confirmar Cita", JOptionPane.QUESTION_MESSAGE);
        if (input != null && !input.isEmpty()) {
            try {
                int idCita = Integer.parseInt(input);
                Cita citaConfirmar = null;

                for (Cita cita : citasProgramadas) {
                    if (cita.getIdCita() == idCita) {
                        citaConfirmar = cita;
                        break;
                    }
                }

                if (citaConfirmar != null) {
                    citaConfirmar.confirmarCita();
                    citasProgramadas.remove(citaConfirmar);
                    citasConfirmadas.add(citaConfirmar);
                    JOptionPane.showMessageDialog(this, "Cita con ID " + idCita + " confirmada con éxito.");
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró una cita con el ID especificado.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El ID ingresado no es válido. Intente nuevamente.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Operación cancelada.");
        }
    }

    private void eliminarCitaProgramada() {
        try {
            int idCita = Integer.parseInt(txtIdCitaEliminar.getText());
            Cita citaEliminar = null;

            for (Cita cita : citasProgramadas) {
                if (cita.getIdCita() == idCita) {
                    citaEliminar = cita;
                    break;
                }
            }

            if (citaEliminar != null) {
                citasProgramadas.remove(citaEliminar);
                JOptionPane.showMessageDialog(this, "Cita programada eliminada con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró una cita programada con el ID especificado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar la cita programada.");
        }
    }

    private void eliminarCitaConfirmada() {
        try {
            int idCita = Integer.parseInt(txtIdCitaEliminar.getText());
            Cita citaEliminar = null;

            for (Cita cita : citasConfirmadas) {
                if (cita.getIdCita() == idCita) {
                    citaEliminar = cita;
                    break;
                }
            }

            if (citaEliminar != null) {
                citasConfirmadas.remove(citaEliminar);
                JOptionPane.showMessageDialog(this, "Cita confirmada eliminada con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró una cita confirmada con el ID especificado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar la cita confirmada.");
        }
    }

    private void mostrarCitas(JTextArea textArea, List<Cita> citas) {
        textArea.setText(""); 
        if (citas.isEmpty()) {
            textArea.append("No hay citas programadas/confirmadas.\n");
        } else {
            for (Cita cita : citas) {
                textArea.append("ID: " + cita.getIdCita() + " | Fecha: " + cita.getFecha() + " | Hora: " + cita.getHora() + "\n");
            }
        }
    }

    private void limpiarCampos() {
        txtIdCita.setText("");
        txtFecha.setText("");
        txtHora.setText("");
        txtIdCitaEliminar.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CitasApp app = new CitasApp();
            app.setVisible(true);
        });
    }
}
