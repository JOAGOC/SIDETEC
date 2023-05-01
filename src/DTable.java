
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tzihual
 */
public class DTable extends DefaultTableCellRenderer{
     Color background;
Color foreground;
public DTable (Color background,Color foreground) {
super();
this.background = background;
this.foreground = foreground;
}
public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {
Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
comp.setBackground(background);
comp.setForeground(foreground);
return comp;
}
}
