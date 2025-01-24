package org.example.admin;

import org.example.cinema.Cinema;
import org.example.member.Admin;
import org.example.member.User;
import org.example.database.UserDatabaseManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class AdminUserManagementPanel {
    private Cinema cinema;
    private Admin admin;
    private boolean deleteMode;
    private UserDatabaseManager userDatabaseManager;

    public AdminUserManagementPanel(Cinema cinema) {
        this(cinema, false);
    }

    public AdminUserManagementPanel(Cinema cinema, boolean deleteMode) {
        this.cinema = cinema;
        this.admin = new Admin(cinema);
        this.deleteMode = deleteMode;
        this.userDatabaseManager = new UserDatabaseManager(); // Initialize UserDatabaseManager
        createUserManagementFrame();
    }

    private void createUserManagementFrame() {
        JFrame frame = new JFrame(deleteMode ? "Usuń Klienta" : "Wyświetl Klientów");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        if (deleteMode) {
            // Pobieranie użytkowników z bazy danych
            List<User> uzytkownicy = userDatabaseManager.pobierzUzytkownikowZBazy();

            // Dodawanie rozwijanego menu z dostępnymi klientami
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(new JLabel("Wybierz klienta:"), gbc);
            gbc.gridx = 1;
            JComboBox<String> userComboBox = new JComboBox<>();
            userComboBox.addItem("---"); // Adding initial "---" option
            for (User user : uzytkownicy) {
                userComboBox.addItem(user.getImie() + " " + user.getNazwisko());
            }
            userComboBox.setPreferredSize(new Dimension(300, 25));
            panel.add(userComboBox, gbc);

            // Pola do wyświetlenia informacji o kliencie
            JTextField imieField = new JTextField(20);
            JTextField nazwiskoField = new JTextField(20);
            JTextField emailField = new JTextField(20);

            userComboBox.addActionListener(e -> {
                String selectedUser = (String) userComboBox.getSelectedItem();
                if (selectedUser != null && !selectedUser.equals("---")) {
                    User user = uzytkownicy.stream().filter(u -> (u.getImie() + " " + u.getNazwisko()).equals(selectedUser)).findFirst().orElse(null);
                    if (user != null) {
                        imieField.setText(user.getImie());
                        nazwiskoField.setText(user.getNazwisko());
                        emailField.setText(user.getEmail());
                    }
                } else {
                    imieField.setText("");
                    nazwiskoField.setText("");
                    emailField.setText("");
                }
            });

            // Dodawanie pól do panelu
            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(new JLabel("Imię:"), gbc);
            gbc.gridx = 1;
            panel.add(imieField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            panel.add(new JLabel("Nazwisko:"), gbc);
            gbc.gridx = 1;
            panel.add(nazwiskoField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            panel.add(new JLabel("Email:"), gbc);
            gbc.gridx = 1;
            panel.add(emailField, gbc);

            // Przycisk do usunięcia klienta
            JButton btnUsunKlienta = new JButton("Usuń klienta");
            btnUsunKlienta.addActionListener(e -> {
                String email = emailField.getText();
                admin.usunUzytkownika(email);
                JOptionPane.showMessageDialog(frame, "Klient został usunięty.");
            });

            gbc.gridy = 4;
            gbc.gridwidth = 2;
            panel.add(btnUsunKlienta, gbc);
        } else {
            JTextArea userTextArea = new JTextArea(20, 50);
            userTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(userTextArea);

            StringBuilder message = new StringBuilder("Lista klientów:\n");
            List<User> uzytkownicy = userDatabaseManager.pobierzUzytkownikowZBazy(); // Fetch users from the database
            for (User user : uzytkownicy) {
                message.append(" - ").append(user.getImie()).append(" ").append(user.getNazwisko()).append("\n");
            }
            userTextArea.setText(message.toString());

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            panel.add(scrollPane, gbc);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
