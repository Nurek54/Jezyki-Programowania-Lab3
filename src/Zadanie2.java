import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*Treść zadania:
W Twoim interfejsie Swing umożliw użytkownikom dynamiczną zmianę koloru,
rozmiaru i pozycji komponentów. Dodaj przyciski, które, po kliknięciu,
zmienią kolor określonego komponentu, zmienią jego rozmiar lub przesuną wewnątrz JFrame.
Możesz użyć JDialog lub JOptionPane do pobierania od użytkownika informacji (np. wyboru koloru).
 */

public class Zadanie2 extends JFrame {

    private JTextField textField;
    private JTextArea textArea;

    public Zadanie2() {
        setTitle("Dynamiczna Zmiana Komponentów");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = createPanel();
        add(panel);

        setLocationRelativeTo(null);
    }

    private JPanel createPanel() {
        // Ustawienia panelu
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        panel.add(new JLabel("Wprowadź tekst:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        textField = new JTextField("Tekst");
        panel.add(textField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Wprowadź obszar tekstu:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        textArea = new JTextArea("Obszar tekstu");
        panel.add(textArea, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        JButton colorButton = new JButton("Zmień kolor");
        JButton sizeButton = new JButton("Zmień rozmiar");
        JButton positionButton = new JButton("Przesuń");
        JButton resetButton = new JButton("Resetuj");

        panel.add(colorButton, gbc);

        gbc.gridy = 3;
        panel.add(sizeButton, gbc);

        gbc.gridy = 4;
        panel.add(positionButton, gbc);

        gbc.gridy = 5;
        panel.add(resetButton, gbc);

        // Dodanie obsługi dla przycisku zmiany koloru
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(Zadanie2.this, "Wybierz kolor", Color.BLACK);
                if (selectedColor != null) {
                    textField.setForeground(selectedColor);
                    textArea.setForeground(selectedColor);
                }
            }
        });

        // Dodanie obsługi dla przycisku zmiany rozmiaru
        sizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(Zadanie2.this, "Podaj nowy rozmiar (np. 14):");
                try {
                    int newSize = Integer.parseInt(input);
                    textField.setFont(textField.getFont().deriveFont((float) newSize));
                    textArea.setFont(textArea.getFont().deriveFont((float) newSize));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Zadanie2.this, "Nieprawidłowy format rozmiaru.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Dodanie obsługi dla przycisku zmiany pozycji
        positionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newX = textField.getX() + 20;
                int newY = textField.getY() + 20;
                textField.setLocation(newX, newY);

                int newAreaX = textArea.getX() - 20;
                int newAreaY = textArea.getY() - 20;
                textArea.setLocation(newAreaX, newAreaY);
            }
        });

        // Dodanie obsługi przycisku reset
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setForeground(Color.BLACK);
                textArea.setForeground(Color.BLACK);
                textField.setFont(new Font("Arial", Font.PLAIN, 12));
                textArea.setFont(new Font("Arial", Font.PLAIN, 12));
                textField.setLocation(0, 0);
                textArea.setLocation(0, 0);
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        // Uruchomienie aplikacji w wątku obsługi zdarzeń
        SwingUtilities.invokeLater(() -> {
            Zadanie2 app = new Zadanie2();
            app.setVisible(true);
        });
    }
}
