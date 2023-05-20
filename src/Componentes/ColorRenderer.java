package Componentes;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AXELS
 */
public class ColorRenderer extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // Obtener el valor de la celda
        String estado = (String) value;
        
        // Establecer el color de fondo de la celda según el valor
        if (estado.equals("Disponible")) {
            c.setBackground(Color.GREEN);
            c.setForeground(Color.BLACK);
        } else if (estado.equals("Confirmada")) {
            c.setBackground(Color.RED);
            c.setForeground(Color.WHITE);
        } else if (estado.equals("Confirmar")) {
            c.setBackground(Color.ORANGE);
            c.setForeground(Color.BLACK);
        } else if (estado.equals("Finalizada")) {
            c.setBackground(Color.BLUE);
            c.setForeground(Color.WHITE);
        }
        
        return c;
    }
}
    

