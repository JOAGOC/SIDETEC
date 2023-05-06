
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;

public class AutoCompleteTextField extends JTextField {

    private static List<String> suggestions = new ArrayList<>();
    private static JComboBox<String> comboBox = new JComboBox<>();
    public static List<String> getSuggestions() {
        return suggestions;
    }

    public static void setSuggestions(List<String> suggestions) {
        AutoCompleteTextField.suggestions = suggestions;
    }

    public static JComboBox<String> getComboBox() {
        return comboBox;
    }

    public static void setComboBox(JComboBox<String> comboBox) {
        AutoCompleteTextField.comboBox = comboBox;
    }

    private String[] items = {"apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "kiwi",
        "lemon", "mango", "nectarine", "orange", "pear", "quince", "raspberry", "strawberry", "tangerine", "ugli",
        "vanilla", "watermelon", "xylocarp", "yellow apple", "zucchini"};

    public AutoCompleteTextField(Document doc, String text, int columns) {
        super(doc, text, columns);
        initialize();
    }

    public AutoCompleteTextField(String s) {
        super(s);
        initialize();
    }

    public AutoCompleteTextField() {
        super();
        initialize();
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    private void initialize() {
        SwingUtilities.invokeLater(() -> {
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            for (String item : items) {
                model.addElement(item);
            }

            this.getParent().add(comboBox);
            comboBox.setModel(model);
            setComboBoxSize();
            comboBox.setMaximumRowCount(5);
            comboBox.hidePopup();

            AutoCompleteTextField ac = this;
            this.addComponentListener(new ComponentListener() {
                public void componentResized(ComponentEvent e) {
                    setComboBoxSize();
                }
                public void componentMoved(ComponentEvent e) {
                }
                public void componentShown(ComponentEvent e) {
                }
                public void componentHidden(ComponentEvent e) {
                }
            });
            this.addKeyListener(new KeyAdapter() {
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
                                if ((index + 1) == comboBox.getItemCount()) {
                                    return;
                                }
                                comboBox.setSelectedIndex(index + 1);
                            }
                            break;
                        case KeyEvent.VK_ENTER:
                            if (comboBox.isPopupVisible() && !(comboBox.getSelectedIndex() < 0)) {
                                String selectedItem = (String) comboBox.getSelectedItem();
                                ac.setText(selectedItem);
                                comboBox.setSelectedIndex(-1);
                                comboBox.hidePopup();
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
                                String text = ac.getText().toLowerCase();
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
                                    Rectangle rect = ac.getBounds();
                                    comboBox.hidePopup();
                                    comboBox.showPopup();
                                }
                                comboBox.setSelectedIndex(-1);
                                System.out.println(ac.getBounds());
                                System.out.println(comboBox.getBounds());
                            });
                            break;
                    }
                }
            });
            System.out.println(this.getBounds());
            System.out.println(comboBox.getBounds());            
        });
    }

    private void setComboBoxSize() {
        Rectangle rect = this.getBounds();
        comboBox.setBounds(rect.x, rect.y+rect.height,rect.width,0);
        comboBox.setPreferredSize(new Dimension(rect.width,0));
    }
}