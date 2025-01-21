package org.example.ui;

import org.example.cinema.Cinema;
import org.example.film.Film;
import org.example.database.FilmDatabaseManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FilmManagerPanel {
    private FilmDatabaseManager dbManager;

    public FilmManagerPanel(Cinema cinema) {
        this.dbManager = new FilmDatabaseManager(); // Inicjalizacja FilmDatabaseManager
        createFilmManagerFrame();
    }

    private void createFilmManagerFrame() {
        JFrame frame = new JFrame("Zarządzaj Filmami");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 400); // Powiększenie okna, aby lepiej dopasować treść
        frame.setLocationRelativeTo(null);

        // Dodawanie ikony
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
        JLabel tytulLabel = new JLabel("Tytuł:");
        tytulLabel.setFont(customFont); // Ustawienie czcionki dla etykiety
        panel.add(tytulLabel, gbc);

        gbc.gridx = 1;
        JTextField tytulField = new JTextField(30);
        tytulField.setFont(customFont); // Ustawienie czcionki dla JTextField
        panel.add(tytulField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel gatunekLabel = new JLabel("Gatunek:");
        gatunekLabel.setFont(customFont); // Ustawienie czcionki dla etykiety
        panel.add(gatunekLabel, gbc);

        gbc.gridx = 1;
        JTextField gatunekField = new JTextField(30);
        gatunekField.setFont(customFont); // Ustawienie czcionki dla JTextField
        panel.add(gatunekField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel czasTrwaniaLabel = new JLabel("Czas trwania (w min):");
        czasTrwaniaLabel.setFont(customFont); // Ustawienie czcionki dla etykiety
        panel.add(czasTrwaniaLabel, gbc);

        gbc.gridx = 1;
        JTextField czasTrwaniaField = new JTextField(30);
        czasTrwaniaField.setFont(customFont); // Ustawienie czcionki dla JTextField
        panel.add(czasTrwaniaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel rezyserLabel = new JLabel("Reżyser:");
        rezyserLabel.setFont(customFont); // Ustawienie czcionki dla etykiety
        panel.add(rezyserLabel, gbc);

        gbc.gridx = 1;
        JTextField rezyserField = new JTextField(30);
        rezyserField.setFont(customFont); // Ustawienie czcionki dla JTextField
        panel.add(rezyserField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel ocenaLabel = new JLabel("Ocena:");
        ocenaLabel.setFont(customFont); // Ustawienie czcionki dla etykiety
        panel.add(ocenaLabel, gbc);

        gbc.gridx = 1;
        JTextField ocenaField = new JTextField(30);
        ocenaField.setFont(customFont); // Ustawienie czcionki dla JTextField
        panel.add(ocenaField, gbc);

        // Dodawanie przycisku
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        JButton btnDodajFilm = new JButton("Dodaj film");
        btnDodajFilm.setFont(customFont); // Ustawienie czcionki dla przycisku
        btnDodajFilm.setBackground(new Color(255, 182, 193));
        btnDodajFilm.addActionListener(e -> {
            String tytul = tytulField.getText();
            String gatunek = gatunekField.getText();
            int czasTrwania = Integer.parseInt(czasTrwaniaField.getText());
            String rezyser = rezyserField.getText();
            double ocena = Double.parseDouble(ocenaField.getText());

            Film film = new Film(tytul, gatunek, czasTrwania, rezyser, ocena);

            // Zapisywanie filmu do bazy danych
            dbManager.dodajFilmDoBazy(film);

            JOptionPane.showMessageDialog(frame, "Film dodany!");
        });
        panel.add(btnDodajFilm, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
