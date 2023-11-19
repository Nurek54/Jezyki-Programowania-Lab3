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
    private JButton zmienKolorButton;
    private JButton zmienRozmiarButton;
    private JButton przesunButton;
    private JButton resetButton;
    private JLabel witajLabel;
    private Point aktualnaPozycja;

    public Zadanie2() {
        setTitle("Zadanie2 - Dynamiczne zmiany komponentów");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        witajLabel = new JLabel("PIWO", SwingConstants.CENTER);
        add(witajLabel, BorderLayout.CENTER);

        zmienKolorButton = new JButton("Zmień kolor");
        zmienRozmiarButton = new JButton("Zmień rozmiar");
        przesunButton = new JButton("Przesuń");
        resetButton = new JButton("Reset");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(zmienKolorButton);
        buttonPanel.add(zmienRozmiarButton);
        buttonPanel.add(przesunButton);
        buttonPanel.add(resetButton);

        add(buttonPanel, BorderLayout.SOUTH);

        zmienKolorButton.addActionListener(e -> zmienKolor(witajLabel));
        zmienRozmiarButton.addActionListener(e -> zmienRozmiar(witajLabel));
        przesunButton.addActionListener(e -> przesunKomponent(witajLabel));
        resetButton.addActionListener(e -> resetKomponent(witajLabel));

        // Ustaw okno na pełny ekran
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setResizable(true);

        // Inicjalizacja aktualnej pozycji
        aktualnaPozycja = witajLabel.getLocation();
    }

    private void zmienKolor(Component komponent) {
        Color wybranyKolor = JColorChooser.showDialog(this, "Wybierz kolor", komponent.getForeground());
        if (wybranyKolor != null) {
            komponent.setForeground(wybranyKolor);
        }
    }

    private void zmienRozmiar(Component komponent) {
        String nowyRozmiarStr = JOptionPane.showInputDialog(this, "Podaj nowy rozmiar (liczba):");
        if (nowyRozmiarStr != null && !nowyRozmiarStr.isEmpty()) {
            int nowyRozmiar = Integer.parseInt(nowyRozmiarStr);
            Font aktualnaCzcionka = komponent.getFont();
            komponent.setFont(new Font(aktualnaCzcionka.getName(), aktualnaCzcionka.getStyle(), nowyRozmiar));
            // Ręczne przesunięcie etykiety na aktualne współrzędne
            komponent.setLocation(aktualnaPozycja);
        }
    }

    private void przesunKomponent(Component komponent) {
        String noweXStr = JOptionPane.showInputDialog(this, "Podaj nową pozycję X (liczba):");
        String noweYStr = JOptionPane.showInputDialog(this, "Podaj nową pozycję Y (liczba):");

        if (noweXStr != null && !noweXStr.isEmpty() && noweYStr != null && !noweYStr.isEmpty()) {
            int noweX = Integer.parseInt(noweXStr);
            int noweY = Integer.parseInt(noweYStr);
            komponent.setLocation(noweX, noweY);
            // Aktualizacja aktualnej pozycji
            aktualnaPozycja = new Point(noweX, noweY);
        }
    }

    private void resetKomponent(Component komponent) {
        witajLabel.setText("PIWO");
        komponent.setForeground(Color.BLACK);
        komponent.setFont(new Font(komponent.getFont().getName(), Font.PLAIN, 12));
        // Przywróć etykietę do ostatniej znanej pozycji
        komponent.setLocation(aktualnaPozycja);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Zadanie2().setVisible(true));
    }
}
