import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*Treść zadania:
Stwórz prostą aplikację GUI w Swing, która zawiera JFrame z tytułem,
rozmiarem i opcją zamykania. Wewnątrz JFrame dodaj JPanel z kilkoma komponentami:
JLabel, JTextField, JPasswordField, JButton i JTextArea.
Pozwól użytkownikom wprowadzać tekst w polach tekstowych i wyświetlać wprowadzone
dane w JTextArea po kliknięciu przycisku.
 */

public class Zadanie1 extends JFrame {

    private JTextField textField;
    private JPasswordField passwordField;
    private JTextArea textArea;

    public Zadanie1() {
        setTitle("Aplikacja GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = createPanel();
        add(panel);

        setLocationRelativeTo(null);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Wprowadź tekst:"));
        textField = new JTextField();
        panel.add(textField);

        panel.add(new JLabel("Wprowadź hasło:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton button = new JButton("Wyświetl w JTextArea");
        panel.add(button);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                char[] password = passwordField.getPassword();
                //String password = passwordField.getPassword();

                textArea.append("Tekst: " + text + "\n");
                textArea.append("Hasło: " + password + "\n");

                textField.setText("");
                passwordField.setText("");
            }
        });

        return panel;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Zadanie1 app = new Zadanie1();
            app.setVisible(true);
        });
    }
}
