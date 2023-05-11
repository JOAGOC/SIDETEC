import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AutoCompleteTextFieldExample {
    private static final String[] items = { "apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "kiwi",
            "lemon", "mango", "nectarine", "orange", "pear", "quince", "raspberry", "strawberry", "tangerine", "ugli",
            "vanilla", "watermelon", "xylocarp", "yellow apple", "zucchini" };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Autocomplete Test");
            JPanel panel = new JPanel(new BorderLayout());
            JTextField textField = new JTextField();
            List<String> suggestions = new ArrayList<>();
            JComboBox<String> comboBox = new JComboBox<>();

            panel.add(textField, BorderLayout.NORTH);
            panel.add(comboBox, BorderLayout.SOUTH);

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (String item : items) {
                model.addElement(item);
            }

            comboBox.setModel(model);
            comboBox.setPreferredSize(new Dimension(textField.getPreferredSize().width, 0));
            comboBox.setMaximumRowCount(5);
            comboBox.hidePopup();

            textField.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            int index = comboBox.getSelectedIndex();
                            if (comboBox.isPopupVisible() && !(index <= 0)) {
                                comboBox.setSelectedIndex(index - 1);
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            if (!comboBox.isPopupVisible()) {
                                comboBox.showPopup();
                            } else {
                                index = comboBox.getSelectedIndex();
                                if ((index + 1) == comboBox.getItemCount())
                                    return;
                                comboBox.setSelectedIndex(index + 1);
                            }
                            break;
                        case KeyEvent.VK_ENTER:
                            if (comboBox.isPopupVisible() && !(comboBox.getSelectedIndex()<=0)) {
                                seleccionar(textField, comboBox);
                            }
                            break;
                        case KeyEvent.VK_ESCAPE:
                            if (comboBox.isPopupVisible()) {
                                comboBox.setSelectedIndex(-1);
                                comboBox.hidePopup();
                            }
                            break;
                        default:
                            SwingUtilities.invokeLater(() -> {
                                String text = textField.getText().toLowerCase();
                                suggestions.clear();
                                for (String item : items) {
                                    if (item.toLowerCase().startsWith(text)) {
                                        suggestions.add(item);
                                    }
                                }
                                if (suggestions.isEmpty()) {
                                    comboBox.hidePopup();
                                } else {
                                    model.removeAllElements();
                                    for (String suggestion : suggestions) {
                                        model.addElement(suggestion);
                                    }
                                    comboBox.showPopup();
                                }
                                comboBox.setPreferredSize(new Dimension(textField.getPreferredSize().width,
                                        comboBox.getPreferredSize().height));
                                comboBox.setSelectedIndex(-1);
                            });
                            break;
                    }
                }

            });
            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    private static void seleccionar(JTextField textField, JComboBox<String> comboBox) {
        String selectedItem = (String) comboBox.getSelectedItem();
        textField.setText(selectedItem);
        comboBox.setSelectedIndex(-1);
        comboBox.hidePopup();
    }
}