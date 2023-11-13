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
        // Ustawienia głównego okna JFrame
        setTitle("Aplikacja GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Dodajemy panel z komponentami do głównego okna
        JPanel panel = createPanel();
        add(panel);

        // Wyśrodkuj okno na ekranie
        setLocationRelativeTo(null);
    }

    private JPanel createPanel() {
        // Ustawienia panelu
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Dodajemy komponenty do panelu
        panel.add(new JLabel("Wprowadź tekst:"));
        textField = new JTextField();
        panel.add(textField);

        panel.add(new JLabel("Wprowadź hasło:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton button = new JButton("Wyświetl w JTextArea");
        panel.add(button);

        // Inicjalizacja JTextArea i dodanie go wraz z JScrollPane
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);

        // Dodanie obsługi zdarzenia dla przycisku
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pobierz tekst z JTextField i JPasswordField
                String text = textField.getText();
                char[] password = passwordField.getPassword();

                // Wyświetl tekst w JTextArea
                textArea.append("Tekst: " + text + "\n");
                textArea.append("Hasło: " + new String(password) + "\n");

                // Wyczyść pola tekstowe
                textField.setText("");
                passwordField.setText("");
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        // Uruchomienie aplikacji w wątku obsługi zdarzeń
        SwingUtilities.invokeLater(() -> {
            Zadanie1 app = new Zadanie1();
            app.setVisible(true);
        });
    }
}
