import javax.swing.*;

/*Treść zadania:
Stwórz okno dialogowe, które prosi użytkownika o podanie swojego imienia.
Po kliknięciu "OK" wyświetl wiadomość powitalną w głównym oknie za pomocą JOptionPane
lub niestandardowego okna dialogowego (JDialog). Upewnij się, że okno dialogowe można łatwo zamknąć,
a wprowadzone imię może być wyświetlane w głównym oknie.
 */

public class Zadanie3 {

    private JFrame frame;

    public Zadanie3() {
        frame = new JFrame("Witaj!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createAndShowGUI() {
        String userName = askForName();

        JLabel welcomeLabel = new JLabel("Witaj, " + userName + " !!!");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        frame.getContentPane().add(welcomeLabel);

        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private String askForName() {
        JTextField nameField = new JTextField();
        Object[] message = {"Podaj swoje imię:", nameField};

        int option = JOptionPane.showOptionDialog(null, message, "Podaj swoje imię", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (option == JOptionPane.OK_OPTION) {
            return nameField.getText();
        } else {
            System.exit(0);
            return null;
        }
    }

    public void runApplication() {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    public static void main(String[] args) {
        Zadanie3 app = new Zadanie3();
        app.runApplication();
    }
}
