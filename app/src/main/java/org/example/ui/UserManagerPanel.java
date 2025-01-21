package org.example.ui;

import org.example.cinema.Cinema;
import org.example.member.User;
import org.example.database.UserDatabaseManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UserManagerPanel {
    private Cinema cinema;
    private UserDatabaseManager dbManager;

    public UserManagerPanel(Cinema cinema) {
        this.cinema = cinema;
        this.dbManager = new UserDatabaseManager();
        createUserManagerFrame();
    }

    private void createUserManagerFrame() {
        JFrame frame = new JFrame("Zarządzaj Użytkownikami");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 350); // Powiększenie okna, aby lepiej dopasować treść
        frame.setLocationRelativeTo(null);

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/icon.jpg"));
            frame.setIconImage(icon.getImage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nie udało się załadować ikony.");
        }

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font customFont = new Font("Arial", Font.BOLD, 20); // Ustalanie czcionki

        // Dodawanie etykiet i pól tekstowych
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel imieLabel = new JLabel("Imię:");
        imieLabel.setFont(customFont); // Ustawienie czcionki dla etykiety
        panel.add(imieLabel, gbc);

        gbc.gridx = 1;
        JTextField imieField = new JTextField(20);
        imieField.setFont(customFont); // Ustawienie czcionki dla JTextField
        panel.add(imieField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel nazwiskoLabel = new JLabel("Nazwisko:");
        nazwiskoLabel.setFont(customFont); // Ustawienie czcionki dla etykiety
        panel.add(nazwiskoLabel, gbc);

        gbc.gridx = 1;
        JTextField nazwiskoField = new JTextField(20);
        nazwiskoField.setFont(customFont); // Ustawienie czcionki dla JTextField
        panel.add(nazwiskoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(customFont); // Ustawienie czcionki dla etykiety
        panel.add(emailLabel, gbc);

        gbc.gridx = 1;
        JTextField emailField = new JTextField(20);
        emailField.setFont(customFont); // Ustawienie czcionki dla JTextField
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel numerTelefonuLabel = new JLabel("Numer telefonu:");
        numerTelefonuLabel.setFont(customFont); // Ustawienie czcionki dla etykiety
        panel.add(numerTelefonuLabel, gbc);

        gbc.gridx = 1;
        JTextField numerTelefonuField = new JTextField(20);
        numerTelefonuField.setFont(customFont); // Ustawienie czcionki dla JTextField
        panel.add(numerTelefonuField, gbc);

        // Dodawanie przycisku
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton btnDodajUzytkownika = new JButton("Dodaj użytkownika");
        btnDodajUzytkownika.setFont(customFont); // Ustawienie czcionki dla przycisku
        btnDodajUzytkownika.setBackground(new Color(173, 216, 230));
        btnDodajUzytkownika.addActionListener(e -> {
            String imie = imieField.getText();
            String nazwisko = nazwiskoField.getText();
            String email = emailField.getText();
            String numerTelefonu = numerTelefonuField.getText();

            User user = new User(imie, nazwisko, email, numerTelefonu);
            dbManager.dodajUzytkownikaDoBazy(user);

            JOptionPane.showMessageDialog(frame, "Użytkownik dodany!");
        });
        panel.add(btnDodajUzytkownika, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
