import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*Treść zadania:
Stwórz prosty interfejs kalkulatora,
który zawiera przyciski z cyframi i podstawowymi operacjami
(dodawanie, odejmowanie, mnożenie, dzielenie).
Pozwól użytkownikom wykonywać obliczenia i wyświetlać wyniki w polu JTextField lub JLabel.
Upewnij się, że elementy GUI są odpowiednio dostosowane pod względem rozmiaru i pozycji.
 */

public class Zadanie4 extends JFrame {
    private JTextField display;
    private double firstNumber;
    private String operator;

    public Zadanie4() {
        firstNumber = 0.0;
        operator = "";

        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Kalkulator");
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Calibri", Font.PLAIN, 24));
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C"
        };

        for (String label : buttonLabels) {
            JButton button = createButton(label);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(new ButtonClickListener());
        button.setFont(new Font("Calibri", Font.PLAIN, 20));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(51, 51, 51));

        return button;
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String command = source.getText();

            switch (command) {
                case "=":
                    calculateResult();
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    setOperation(command);
                    break;
                case ".":
                    appendToInput(".");
                    break;
                case "C": // Zmieniono na "Clear"
                    clearCalculator();
                    display.setText("");
                    break;
                default:
                    appendToInput(command);
                    break;
            }
        }
    }

    private void appendToInput(String input) {
        display.setText(display.getText() + input);
    }

    private void setOperation(String op) {
        if (!display.getText().isEmpty()) {
            firstNumber = Double.parseDouble(display.getText());
            operator = op;
            display.setText("");
        }
    }

    private void calculateResult() {
        if (!display.getText().isEmpty() && !operator.isEmpty()) {
            double secondNumber = Double.parseDouble(display.getText());
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "*":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        JOptionPane.showMessageDialog(null, "Nie można dzielić przez zero !!!", "Błąd", JOptionPane.ERROR_MESSAGE);
                        clearCalculator();
                        return;
                    }
                    break;
            }

            display.setText(Double.toString(result));
            clearCalculator();
        }
    }

    private void clearCalculator() {
        firstNumber = 0.0;
        operator = "";
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new Zadanie4());
    }
}
