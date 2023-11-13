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
        // Ustawienia głównego okna JFrame
        setTitle("Dynamiczna Zmiana Komponentów");
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
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Dodajemy komponenty do panelu
        panel.add(new JLabel("Wprowadź tekst:"));
        textField = new JTextField("Tekst");
        panel.add(textField);

        panel.add(new JLabel("Wprowadź obszar tekstu:"));
        textArea = new JTextArea("Obszar tekstu");
        panel.add(textArea);

        JButton colorButton = new JButton("Zmień kolor");
        JButton sizeButton = new JButton("Zmień rozmiar");
        JButton positionButton = new JButton("Przesuń");

        panel.add(colorButton);
        panel.add(sizeButton);
        panel.add(positionButton);

        // Dodanie obsługi zdarzenia dla przycisku zmiany koloru
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

        // Dodanie obsługi zdarzenia dla przycisku zmiany rozmiaru
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

        // Dodanie obsługi zdarzenia dla przycisku zmiany pozycji
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
