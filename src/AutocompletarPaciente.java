/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author memac
 */
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.text.JTextComponent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class AutocompletarPaciente {
    //Paciente paciente = new Paciente();

    public void MostrarDatos(final JComboBox combobox){
        combobox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
           @Override 
            public void keyPressed(KeyEvent evt) {
                String cadenaEscrita = combobox.getEditor().getItem().toString().toLowerCase(); // Convierte la cadena a minúsculas

                // Permitimos la entrada de cualquier carácter alfabético, el espacio y la tecla retroceso
                if (Character.isAlphabetic(evt.getKeyChar()) || evt.getKeyCode() == KeyEvent.VK_SPACE || evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    //combobox.setModel(paciente.ObtenerLista(cadenaEscrita));
                    if (combobox.getItemCount() > 0) { 
                        combobox.showPopup();

                        if(evt.getKeyCode() != KeyEvent.VK_BACK_SPACE){
                            ((JTextComponent)combobox.getEditor().getEditorComponent()).select(cadenaEscrita.length(), combobox.getEditor().getItem().toString().length());
                        }else{
                            combobox.getEditor().setItem(cadenaEscrita);
                        }

                    } else {
                        combobox.addItem(cadenaEscrita);
                    }
                }
            } 
        });
    }
}



