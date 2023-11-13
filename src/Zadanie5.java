import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Zadanie5 extends JFrame {

    private JTextField passwordField;

    public Zadanie5() {
        // Ustawienia głównego okna JFrame
        setTitle("Sprawdzanie Siły Hasła");
        setSize(300, 150);
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
        panel.setLayout(new BorderLayout());

        // Pole do wprowadzania hasła
        passwordField = new JPasswordField();
        panel.add(passwordField, BorderLayout.CENTER);

        // Przycisk do sprawdzania siły hasła
        JButton checkButton = new JButton("Sprawdź Siłę Hasła");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkPasswordStrength();
            }
        });
        panel.add(checkButton, BorderLayout.SOUTH);

        return panel;
    }

    private void checkPasswordStrength() {
        String password = passwordField.getText();
        String strengthCategory = assessPasswordStrength(password);
        String resultMessage = "Siła Hasła: " + strengthCategory;

        JOptionPane.showMessageDialog(this, resultMessage, "Ocena Siły Hasła", JOptionPane.INFORMATION_MESSAGE);
    }

    private String assessPasswordStrength(String password) {
        int length = password.length();
        boolean containsDigits = password.matches(".*\\d.*");
        boolean containsLowerCase = password.matches(".*[a-z].*");
        boolean containsUpperCase = password.matches(".*[A-Z].*");
        boolean containsSpecialChars = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");

        if (length < 6) {
            return "Bardzo Słabe";
        } else if (length >= 6 && !containsDigits) {
            return "Słabe";
        } else if (length >= 6 && containsDigits && (!containsUpperCase || !containsLowerCase)) {
            return "Średnie";
        } else if (length >= 8 && containsDigits && containsUpperCase && containsLowerCase && containsSpecialChars) {
            return "Bardzo Silne";
        } else {
            return "Silne";
        }
    }

    public static void main(String[] args) {
        // Uruchomienie aplikacji w wątku obsługi zdarzeń
        SwingUtilities.invokeLater(() -> {
            Zadanie5 passwordStrengthChecker = new Zadanie5();
            passwordStrengthChecker.setVisible(true);
        });
    }
}