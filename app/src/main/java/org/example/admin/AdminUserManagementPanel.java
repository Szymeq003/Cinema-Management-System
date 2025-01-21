package org.example.admin;

import org.example.cinema.Cinema;
import org.example.member.Admin;
import org.example.member.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminUserManagementPanel {
    private Cinema cinema;
    private Admin admin;
    private boolean deleteMode;

    public AdminUserManagementPanel(Cinema cinema) {
        this(cinema, false);
    }

    public AdminUserManagementPanel(Cinema cinema, boolean deleteMode) {
        this.cinema = cinema;
        this.admin = new Admin(cinema);
        this.deleteMode = deleteMode;
        createUserManagementFrame();
    }

    private void createUserManagementFrame() {
        JFrame frame = new JFrame(deleteMode ? "Usuń Użytkownika" : "Wyświetl Użytkowników");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        if (deleteMode) {
            JTextField emailField = new JTextField(20);
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(new JLabel("Email:"), gbc);
            gbc.gridx = 1;
            panel.add(emailField, gbc);

            JButton btnUsunUzytkownika = new JButton("Usuń użytkownika");
            btnUsunUzytkownika.addActionListener(e -> {
                String email = emailField.getText();
                admin.usunUzytkownika(email);
            });
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            panel.add(btnUsunUzytkownika, gbc);
        } else {
            JTextArea userTextArea = new JTextArea(20, 50);
            userTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(userTextArea);

            StringBuilder message = new StringBuilder("Lista użytkowników:\n");
            for (User user : cinema.getUzytkownicy()) {
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
