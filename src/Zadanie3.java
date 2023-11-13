import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*Treść zadania:
Stwórz okno dialogowe, które prosi użytkownika o podanie swojego imienia.
Po kliknięciu "OK" wyświetl wiadomość powitalną w głównym oknie za pomocą
JOptionPane lub niestandardowego okna dialogowego (JDialog). Upewnij się,
że okno dialogowe można łatwo zamknąć, a wprowadzone imię może być wyświetlane w głównym oknie.
 */

public class Zadanie3 extends JFrame {

    private JLabel welcomeLabel;

    public Zadanie3() {
        // Ustawienia głównego okna JFrame
        setTitle("Okno Dialogowe z Imieniem");
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
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Dodajemy komponenty do panelu
        JButton showDialogButton = new JButton("Pokaż Okno Dialogowe");
        panel.add(showDialogButton);

        welcomeLabel = new JLabel("Witaj, ");
        panel.add(welcomeLabel);

        // Dodanie obsługi zdarzenia dla przycisku pokazującego okno dialogowe
        showDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNameInputDialog();
            }
        });

        return panel;
    }

    private void showNameInputDialog() {
        // Tworzymy okno dialogowe
        JDialog nameDialog = new JDialog(this, "Podaj Imię", true);
        nameDialog.setSize(300, 150);
        nameDialog.setLayout(new FlowLayout());
        nameDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Dodajemy komponenty do okna dialogowego
        JLabel nameLabel = new JLabel("Imię:");
        JTextField nameField = new JTextField(20);
        JButton okButton = new JButton("OK");

        nameDialog.add(nameLabel);
        nameDialog.add(nameField);
        nameDialog.add(okButton);

        // Dodanie obsługi zdarzenia dla przycisku OK
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredName = nameField.getText();
                if (!enteredName.isEmpty()) {
                    welcomeLabel.setText("Witaj, " + enteredName + "!");
                } else {
                    JOptionPane.showMessageDialog(Zadanie3.this, "Proszę podać imię.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
                nameDialog.dispose(); // Zamknięcie okna dialogowego po wprowadzeniu imienia
            }
        });

        // Wyśrodkuj okno dialogowe na ekranie
        nameDialog.setLocationRelativeTo(this);

        // Ustawienia widoczności okna dialogowego
        nameDialog.setVisible(true);
    }

    public static void main(String[] args) {
        // Uruchomienie aplikacji w wątku obsługi zdarzeń
        SwingUtilities.invokeLater(() -> {
            Zadanie3 app = new Zadanie3();
            app.setVisible(true);
        });
    }
}
