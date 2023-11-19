import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/*Treść zadania:
Stwórz GUI do sprawdzania siły hasła. Udostępnij pole tekstowe do wprowadzania haseł oraz przycisk
do sprawdzania siły hasła. Zaimplementuj logikę oceny siły hasła (np. długość, rodzaje znaków)
i wyświetl wynik w oknie dialogowym lub na interfejsie.
 */

public class Zadanie5 extends JFrame {

    private JPasswordField passwordField;
    private JButton checkStrengthButton;

    public Zadanie5() {
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sprawdzanie Siły Hasła");
        setLayout(new BorderLayout());

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        add(passwordField, BorderLayout.NORTH);

        checkStrengthButton = new JButton("Sprawdź Siłę Hasła");
        checkStrengthButton.addActionListener(new ButtonClickListener());
        checkStrengthButton.setFont(new Font("Arial", Font.PLAIN, 16));
        add(checkStrengthButton, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);
            String strengthCategory = assessPasswordStrength(password);
            displayStrengthMessage(strengthCategory);
            Arrays.fill(passwordChars, ' ');
            passwordField.setText("");
        }
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

    private void displayStrengthMessage(String strengthCategory) {
        String message = "Siła Hasła: " + strengthCategory;

        JOptionPane.showMessageDialog(this, message, "Ocena Siły Hasła", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Zadanie5());
    }
}
