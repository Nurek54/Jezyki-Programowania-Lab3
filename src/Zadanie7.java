import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Zadanie7 extends JFrame {

    private JLabel imageLabel;
    private ImageIcon imageIcon;

    public Zadanie7() {
        // Ustawienia głównego okna JFrame
        setTitle("Przeglądarka Obrazów");
        setSize(800, 600);
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

        // Etykieta do wyświetlania obrazu
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        panel.add(new JScrollPane(imageLabel), BorderLayout.CENTER);

        // Pasek narzędziowy z przyciskami
        JToolBar toolBar = createToolBar();
        panel.add(toolBar, BorderLayout.NORTH);

        return panel;
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();

        // Przycisk wczytujący obraz
        JButton openButton = new JButton("Wczytaj");
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadImage();
            }
        });
        toolBar.add(openButton);

        // Przycisk przybliżania
        JButton zoomInButton = new JButton("Powiększ");
        zoomInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoomIn();
            }
        });
        toolBar.add(zoomInButton);

        // Przycisk oddalania
        JButton zoomOutButton = new JButton("Pomniejsz");
        zoomOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoomOut();
            }
        });
        toolBar.add(zoomOutButton);

        // Przycisk dopasowywania obrazu do okna
        JButton fitToScreenButton = new JButton("Dopasuj do ekranu");
        fitToScreenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fitToScreen();
            }
        });
        toolBar.add(fitToScreenButton);

        return toolBar;
    }

    private void loadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Obrazy", "jpg", "jpeg", "png", "gif"));
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(selectedFile);
                displayImage(image);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Błąd podczas wczytywania obrazu!", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void displayImage(BufferedImage image) {
        imageIcon = new ImageIcon(image);
        imageLabel.setIcon(imageIcon);
    }

    private void zoomIn() {
        if (imageIcon != null) {
            int width = (int) (imageIcon.getIconWidth() * 1.2);
            int height = (int) (imageIcon.getIconHeight() * 1.2);
            scaleImage(width, height);
        }
    }

    private void zoomOut() {
        if (imageIcon != null) {
            int width = (int) (imageIcon.getIconWidth() * 0.8);
            int height = (int) (imageIcon.getIconHeight() * 0.8);
            scaleImage(width, height);
        }
    }

    private void fitToScreen() {
        if (imageIcon != null) {
            int width = getWidth() - 20; // Odstęp na marginesy
            int height = getHeight() - 20;
            scaleImage(width, height);
        }
    }

    private void scaleImage(int width, int height) {
        Image scaledImage = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);
        imageLabel.setIcon(imageIcon);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Zadanie7 imageViewer = new Zadanie7();
            imageViewer.setVisible(true);
        });
    }
}
