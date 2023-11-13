import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*Treść zadania:
Stwórz okno dialogowe, które prosi użytkownika o podanie swojego imienia.
Po kliknięciu "OK" wyświetl wiadomość powitalną w głównym oknie za pomocą JOptionPane
lub niestandardowego okna dialogowego (JDialog). Upewnij się, że okno dialogowe można łatwo zamknąć,
a wprowadzone imię może być wyświetlane w głównym oknie.
 */

public class Zadanie3 extends JFrame {

    private JLabel welcomeLabel;

    public Zadanie3() {
        setTitle("Okno Dialogowe z Imieniem");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = createPanel();
        add(panel);

        setLocationRelativeTo(null);
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton showDialogButton = new JButton("Pokaż Okno Dialogowe");
        panel.add(showDialogButton, BorderLayout.NORTH);

        welcomeLabel = new JLabel("Witaj, ");
        panel.add(welcomeLabel, BorderLayout.CENTER);

        showDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNameInputDialog();
            }
        });

        return panel;
    }

    private void showNameInputDialog() {
        JDialog nameDialog = new JDialog(this, "Podaj Imię", true);
        nameDialog.setSize(300, 150);
        nameDialog.setLayout(new BorderLayout());
        nameDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Imię:");
        JTextField nameField = new JTextField(20);
        JButton okButton = new JButton("OK");

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(okButton);

        nameDialog.add(inputPanel, BorderLayout.CENTER);

        // Dodanie obsługi dla przycisku OK
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

        nameDialog.setLocationRelativeTo(this);

        nameDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Zadanie3 app = new Zadanie3();
            app.setVisible(true);
        });
    }
}
