import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*Treść zadania:
Utwórz aplikację GUI do zarządzania listą kontaktów, w której użytkownicy mogą dodawać,
edytować i usuwać kontakty. Każdy wpis kontaktu powinien zawierać pola na imię,
numer telefonu i adres e-mail. Pozwól użytkownikom modyfikować lub usuwać kontakty
oraz wyświetlaj listę kontaktów w obszarze przewijalnym.
 */

public class Zadanie6 extends JFrame {

    private JTextField nameField, phoneField, emailField;
    private DefaultListModel<Contact> contactListModel;
    private JList<Contact> contactList;

    public Zadanie6() {
        setTitle("Zarządzanie Kontaktami");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contactListModel = new DefaultListModel<>();
        contactList = new JList<>(contactListModel);

        JPanel panel = createPanel();
        add(panel);

        setLocationRelativeTo(null);
    }

    private JPanel createPanel() {
        // Ustawienia panelu
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = createInputPanel();
        panel.add(inputPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(contactList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = createButtonPanel();
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2, 5, 5));

        inputPanel.add(new JLabel("Imię:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Numer telefonu:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);

        inputPanel.add(new JLabel("Adres e-mail:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        return inputPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Dodaj");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });

        JButton editButton = new JButton("Edytuj");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editContact();
            }
        });

        JButton deleteButton = new JButton("Usuń");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteContact();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        return buttonPanel;
    }

    private void addContact() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();

        if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
            Contact contact = new Contact(name, phone, email);
            contactListModel.addElement(contact);

            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Wypełnij wszystkie pola!", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editContact() {
        int selectedIndex = contactList.getSelectedIndex();

        if (selectedIndex != -1) {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();

            if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                Contact contact = contactListModel.get(selectedIndex);
                contact.setName(name);
                contact.setPhone(phone);
                contact.setEmail(email);

                // Odświeżenie widoku listy
                contactList.repaint();

                // Wyczyszczenie pól po edycji kontaktu
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Wypełnij wszystkie pola!", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz kontakt do edycji!", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteContact() {
        int selectedIndex = contactList.getSelectedIndex();

        if (selectedIndex != -1) {
            contactListModel.remove(selectedIndex);
            clearFields(); // Wyczyszczenie pól po usunięciu kontaktu
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz kontakt do usunięcia!", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Zadanie6 contactsManager = new Zadanie6();
            contactsManager.setVisible(true);
        });
    }

    private static class Contact {
        private String name;
        private String phone;
        private String email;

        public Contact(String name, String phone, String email) {
            this.name = name;
            this.phone = phone;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
