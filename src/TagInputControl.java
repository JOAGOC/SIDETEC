import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TagInputControl extends JPanel implements ActionListener {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Mi Aplicación");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        TagInputControl tagInputControl = new TagInputControl();
        frame.getContentPane().add(tagInputControl);
        frame.pack();
        frame.setVisible(true);
    }
    private JTextField tagInputField;
    private JPanel tagPanel;
    private JScrollPane scrollPane;
    private Color buttonColor = Color.ORANGE;
    private List<String> tags = new ArrayList<String>();

    private Color buttonTextColor = Color.BLACK;

    public TagInputControl() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(200, 50));

        tagInputField = new JTextField();
        tagInputField.addActionListener(this);

        tagPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tagPanel.setPreferredSize(new Dimension(0, 0));
        tagPanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(tagPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Agrega esta línea

        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        add(tagInputField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 0.9;
        add(scrollPane, gridBagConstraints);
    }

    public void actionPerformed(ActionEvent e) {
        String tag = limpiarCadena(tagInputField.getText());
        if (!tag.isEmpty()) {
            if (tags.contains(tag)){
                tagInputField.setText("");
                return;
            }
            JButton tagButton = new JButton(tag);

            tagButton.setBackground(buttonColor);
            tagButton.setForeground(buttonTextColor);

            tagButton.setOpaque(true);
            tagButton.setBorderPainted(false);
            tagButton.setFocusPainted(false);

            tagButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    tags.remove(tag);

                    tagPanel.remove(tagButton);
                    tagPanel.revalidate();
                    tagPanel.repaint();

                    SwingUtilities.invokeLater(() -> {
                        if (tagPanel.getComponentCount() == 0)
                            return;
                        Rectangle bounds = tagPanel.getComponents()[tagPanel.getComponentCount() - 1].getBounds();
                        int height = bounds.y + bounds.height + 5;
                        tagPanel.setPreferredSize(new Dimension(0, height));
                        SwingUtilities.invokeLater(() -> {
                            scrollPane.revalidate();
                        });
                    });
                }
            });
            tagPanel.add(tagButton);
            tagPanel.revalidate();
            tagPanel.repaint();

            tagInputField.setText("");
            tags.add(tag);

            SwingUtilities.invokeLater(() -> {
                Rectangle bounds = tagButton.getBounds();
                tagPanel.setPreferredSize(new Dimension(0, bounds.y + bounds.height + 5));
                SwingUtilities.invokeLater(() -> {
                    scrollPane.revalidate();
                });
            });
        }
    }

    public static String limpiarCadena(String s) {
        return s.replaceAll("^\\s+|\\s+$|\\s+(?=\\s)", "");
    }

    public JTextField getTagInputField() {
        return tagInputField;
    }

    public void setTagInputField(JTextField tagInputField) {
        Container c = this.tagInputField.getParent();
        System.out.println((c == null) ? "":c.toString());
        c.remove(this.tagInputField);
        System.out.println((c.equals(this)) ? "Iguales":"Diferentes");
        this.remove(tagInputField);
        this.tagInputField = tagInputField;
        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        add(tagInputField, gridBagConstraints);
        tagInputField.addActionListener(this);
    }

    public JPanel getTagPanel() {
        return tagPanel;
    }

    public void setTagPanel(JPanel tagPanel) {
        this.tagPanel = tagPanel;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public Color getButtonColor() {
        return buttonColor;
    }

    public void setButtonColor(Color buttonColor) {
        this.buttonColor = buttonColor;
    }

    public Color getButtonTextColor() {
        return buttonTextColor;
    }

    public void setButtonTextColor(Color buttonTextColor) {
        this.buttonTextColor = buttonTextColor;
    }

    public List<String> getTags() {
        return tags;
    }
    
    public String getTags(int a){
        return tags.toString().replaceAll("\\[|\\]", "");
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void clear() {
        tagPanel.removeAll();
        tagInputField.setText("");
        tags.clear();
    }

    public void setTags(String tags) {
        String[] tagsArray;
                tagsArray = tags.split(", ");
                for (String s : tagsArray){
                    tagInputField.setText(s);
                    actionPerformed(null);
                }
    }
}