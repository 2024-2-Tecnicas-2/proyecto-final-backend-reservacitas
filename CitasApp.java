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
        setTitle("Gestión de Citas Médicas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

  
        JPanel panelDatos = new JPanel(new GridLayout(5, 2));
        panelDatos.add(new JLabel("ID de Cita:"));
        txtIdCita = new JTextField();
        panelDatos.add(txtIdCita);

        panelDatos.add(new JLabel("Fecha (DD/MM/YYYY):"));
        txtFecha = new JTextField();
        panelDatos.add(txtFecha);

        panelDatos.add(new JLabel("Hora (HH:MM):"));
        txtHora = new JTextField();
        panelDatos.add(txtHora);

        panelDatos.add(new JLabel("Especialidad del Médico:"));
        txtEspecialidadMedico = new JTextField("Cardiología");
        panelDatos.add(txtEspecialidadMedico);

        panelDatos.add(new JLabel("Nombre del Médico:"));
        txtNombreMedico = new JTextField("Dr. Perez");
        panelDatos.add(txtNombreMedico);

        add(panelDatos);

  
        JPanel panelBotones = new JPanel();
        JButton btnProgramarCita = new JButton("Programar Cita");
        JButton btnConfirmarCita = new JButton("Confirmar Cita");
        JButton btnVerCitasProgramadas = new JButton("Ver Citas Programadas");
        JButton btnVerCitasConfirmadas = new JButton("Ver Citas Confirmadas");
        JButton btnLimpiarCampos = new JButton("Limpiar Campos");
        JButton btnEliminarCitaProgramada = new JButton("Eliminar Cita Programada");
        JButton btnEliminarCitaConfirmada = new JButton("Eliminar Cita Confirmada");

        panelBotones.add(btnProgramarCita);
        panelBotones.add(btnConfirmarCita);
        panelBotones.add(btnVerCitasProgramadas);
        panelBotones.add(btnVerCitasConfirmadas);
        panelBotones.add(btnLimpiarCampos);
        panelBotones.add(btnEliminarCitaProgramada);
        panelBotones.add(btnEliminarCitaConfirmada);

        add(panelBotones);

  
        JPanel panelCitas = new JPanel(new GridLayout(1, 2));
        panelCitas.add(new JScrollPane(txtAreaCitasProgramadas = new JTextArea("Citas Programadas:\n")));
        panelCitas.add(new JScrollPane(txtAreaCitasConfirmadas = new JTextArea("Citas Confirmadas:\n")));
        add(panelCitas);

  
        JPanel panelEliminarCita = new JPanel(new BorderLayout());
        panelEliminarCita.setBorder(BorderFactory.createTitledBorder("Eliminar Citas"));
        panelEliminarCita.add(new JLabel("Ingrese ID de la cita a eliminar:"), BorderLayout.NORTH);
        txtIdCitaEliminar = new JTextField();
        panelEliminarCita.add(txtIdCitaEliminar, BorderLayout.CENTER);
        add(panelEliminarCita);

    
        btnProgramarCita.addActionListener(e -> programarCita());
        btnConfirmarCita.addActionListener(e -> confirmarCita());
        btnVerCitasProgramadas.addActionListener(e -> mostrarCitas(txtAreaCitasProgramadas, citasProgramadas));
        btnVerCitasConfirmadas.addActionListener(e -> mostrarCitas(txtAreaCitasConfirmadas, citasConfirmadas));
        btnLimpiarCampos.addActionListener(e -> limpiarCampos());
        btnEliminarCitaProgramada.addActionListener(e -> eliminarCitaProgramada());
        btnEliminarCitaConfirmada.addActionListener(e -> eliminarCitaConfirmada());


        medico = new Medico(txtEspecialidadMedico.getText(), 1, txtNombreMedico.getText());
        paciente = new Paciente(123456789, 30, "Lopez", "Carlos", 2);
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
