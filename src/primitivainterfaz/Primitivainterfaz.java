/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package primitivainterfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author carmunjim3
 */
public class Primitivainterfaz extends JFrame implements ActionListener {

    private int contadorSeleccionados = 0;
    
    // Declaraciones
        JPanel JPcheckboxes, JPbotones;
        JCheckBox[] checkboxes = new JCheckBox[50];
        JButton Bguardar, Bnueva;
        JComboBox CBlista;

    public Primitivainterfaz() {
        //super de JFrame !IMPORTANTE!
        super();

        JPcheckboxes = new JPanel();
        JPcheckboxes.setLayout(new GridLayout(7, 7));
        for (int i = 0; i < 49; i++) { // Bucle corregido para empezar en 0
            checkboxes[i] = new JCheckBox(String.valueOf(i + 1)); // Corrección del label
            JPcheckboxes.add(checkboxes[i]);

            final JCheckBox checkboxActual = checkboxes[i]; // Declaración de checkboxActual

            checkboxes[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (checkboxActual.isSelected()) {
                        if (contadorSeleccionados >= 6) {
                            checkboxActual.setSelected(false);
                            JOptionPane.showMessageDialog(Primitivainterfaz.this, "Solo puedes seleccionar 6 checkboxes.");
                        } else {
                            contadorSeleccionados++;
                        }
                    } else {
                        contadorSeleccionados--;
                    }
                }
            });
        }
        this.add(JPcheckboxes);

        // Panel con los botones
        JPbotones = new JPanel();
        JPbotones.setLayout(new GridLayout(2, 1));
        Bguardar = new JButton("Guardar lista");
        Bguardar.addActionListener(this);
        JPbotones.add(Bguardar);
        Bnueva = new JButton("Nueva primitiva");
        Bnueva.addActionListener(this);
        JPbotones.add(Bnueva);
        this.add(JPbotones);

        // Lista con las combinaciones
        CBlista = new JComboBox<>();
        CBlista.addItem("Vacia");
        CBlista.setSelectedItem("Vacia");
        this.add(CBlista);

        this.setSize(600, 230); // Set frame size
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLayout(new FlowLayout());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtiene la fuente de qué es presionado
        Object quien = e.getSource();
        if (quien == Bnueva) {
            // Deselecciona todas las checkboxes
            for (JCheckBox checkbox : checkboxes) {
                if (checkbox != null) {
                    checkbox.setSelected(false);
                }
            }
            contadorSeleccionados = 0; // Resetea el contador de checkboxes seleccionadas
        } else if (quien == Bguardar) {
            // Lógica para guardar la combinación directamente aquí
            java.util.List<Integer> numerosSeleccionados = new java.util.ArrayList<>();
            for (JCheckBox checkbox : checkboxes) {
                if (checkbox != null && checkbox.isSelected()) {
                    numerosSeleccionados.add(Integer.parseInt(checkbox.getText()));
                }
            }

            if (numerosSeleccionados.size() == 6) {
                StringBuilder combinacion = new StringBuilder();
                for (int i = 0; i < 6; i++) {
                    combinacion.append(numerosSeleccionados.get(i));
                    if (i < 5) {
                        combinacion.append("-");
                    }
                }
                CBlista.addItem(combinacion.toString());
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona exactamente 6 números, ni uno más ni uno menos.");
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        new Primitivainterfaz();
    }
}
