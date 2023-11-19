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

        zmienKolorButton.addActionListener(new Zadanie2.ZmienKolorListener(witajLabel));
        zmienRozmiarButton.addActionListener(new Zadanie2.ZmienRozmiarListener(witajLabel));
        przesunButton.addActionListener(new Zadanie2.PrzesunListener(witajLabel, this));
        resetButton.addActionListener(new Zadanie2.ResetListener(witajLabel));

        // Ustaw okno na pełny ekran
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setResizable(true);

        // Inicjalizacja aktualnej pozycji
        aktualnaPozycja = witajLabel.getLocation();
    }

    private static class ZmienKolorListener implements ActionListener {
        private final Component komponent;

        public ZmienKolorListener(Component komponent) {
            this.komponent = komponent;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Color wybranyKolor = JColorChooser.showDialog(null, "Wybierz kolor", komponent.getForeground());
            if (wybranyKolor != null) {
                komponent.setForeground(wybranyKolor);
            }
        }
    }

    private static class ZmienRozmiarListener implements ActionListener {
        private final Component komponent;

        public ZmienRozmiarListener(Component komponent) {
            this.komponent = komponent;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String nowyRozmiarStr = JOptionPane.showInputDialog(null, "Podaj nowy rozmiar (liczba):");
            if (nowyRozmiarStr != null && !nowyRozmiarStr.isEmpty()) {
                int nowyRozmiar = Integer.parseInt(nowyRozmiarStr);
                Font aktualnaCzcionka = komponent.getFont();
                komponent.setFont(new Font(aktualnaCzcionka.getName(), aktualnaCzcionka.getStyle(), nowyRozmiar));
                // Ręczne przesunięcie etykiety na aktualne współrzędne
                komponent.setLocation(((Zadanie2) SwingUtilities.getWindowAncestor(komponent)).aktualnaPozycja);
            }
        }
    }

    private static class PrzesunListener implements ActionListener {
        private final Component komponent;
        private final JFrame frame;

        public PrzesunListener(Component komponent, JFrame frame) {
            this.komponent = komponent;
            this.frame = frame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String noweXStr = JOptionPane.showInputDialog(null, "Podaj nową pozycję X (liczba):");
            String noweYStr = JOptionPane.showInputDialog(null, "Podaj nową pozycję Y (liczba):");

            if (noweXStr != null && !noweXStr.isEmpty() && noweYStr != null && !noweYStr.isEmpty()) {
                int noweX = Integer.parseInt(noweXStr);
                int noweY = Integer.parseInt(noweYStr);
                komponent.setLocation(noweX, noweY);
                // Aktualizacja aktualnej pozycji
                ((Zadanie2) frame).aktualnaPozycja = new Point(noweX, noweY);
            }
        }
    }

    private static class ResetListener implements ActionListener {
        private final JLabel label;

        public ResetListener(JLabel label) {
            this.label = label;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            label.setText("PIWO");
            label.setForeground(Color.BLACK);
            label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 12));
            // Przywróć etykietę do ostatniej znanej pozycji
            ((Zadanie2) SwingUtilities.getWindowAncestor(label)).aktualnaPozycja = label.getLocation();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new Zadanie2().setVisible(true));
    }
}
