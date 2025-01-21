package org.example.ui;

import org.example.cinema.Cinema;
import org.example.film.Film;
import org.example.screening.Hall;
import org.example.screening.Screening;
import org.example.database.ScreeningDatabaseManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ScreeningManagerPanel {
    private Cinema cinema;
    private ScreeningDatabaseManager dbManager;

    public ScreeningManagerPanel(Cinema cinema) {
        this.cinema = cinema;
        this.dbManager = new ScreeningDatabaseManager();
        createScreeningManagerFrame();
    }

    private void createScreeningManagerFrame() {
        JFrame frame = new JFrame("Zarządzaj Seansami");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 300); // Zmieniamy rozmiar okna na większy, aby lepiej dopasować treść
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

        // Dodawanie rozwijanego menu z dostępnymi filmami
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel tytulFilmuLabel = new JLabel("Tytuł filmu:");
        tytulFilmuLabel.setFont(customFont); // Ustawienie czcionki dla etykiety
        panel.add(tytulFilmuLabel, gbc);

        gbc.gridx = 1;
        List<String> tytulyFilmow = dbManager.pobierzTytulyFilmowZBazy(); // Pobieranie tytułów filmów z bazy danych
        JComboBox<String> filmComboBox = new JComboBox<>(tytulyFilmow.toArray(new String[0]));
        filmComboBox.setPreferredSize(new Dimension(375, 40)); // Ustawienie preferowanej wielkości rozwijanego menu
        filmComboBox.setFont(customFont); // Ustawienie czcionki dla JComboBox
        panel.add(filmComboBox, gbc);

        // Dodawanie rozwijanego menu z dostępnymi salami
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel salaLabel = new JLabel("Sala:");
        salaLabel.setFont(customFont); // Ustawienie czcionki dla etykiety
        panel.add(salaLabel, gbc);

        gbc.gridx = 1;
        String[] sale = {"Sala 1 (100 miejsc)", "Sala 2 (120 miejsc)", "Sala 3 (150 miejsc)", "Sala 4 (80 miejsc)", "Sala 5 (200 miejsc)"};
        JComboBox<String> salaComboBox = new JComboBox<>(sale);
        salaComboBox.setPreferredSize(new Dimension(200, 25)); // Ustawienie preferowanej wielkości rozwijanego menu
        salaComboBox.setFont(customFont); // Ustawienie czcionki dla JComboBox
        panel.add(salaComboBox, gbc);

        // Dodawanie rozwijanego menu dla godziny
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel godzinaLabel = new JLabel("Godzina:");
        godzinaLabel.setFont(customFont); // Ustawienie czcionki dla etykiety
        panel.add(godzinaLabel, gbc);

        gbc.gridx = 1;
        String[] godziny = generateTimeSlots();
        JComboBox<String> godzinaComboBox = new JComboBox<>(godziny);
        godzinaComboBox.setPreferredSize(new Dimension(200, 25)); // Ustawienie preferowanej wielkości rozwijanego menu
        godzinaComboBox.setFont(customFont); // Ustawienie czcionki dla JComboBox
        panel.add(godzinaComboBox, gbc);

        // Dodawanie przycisku
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton btnDodajSeans = new JButton("Dodaj seans");
        btnDodajSeans.setFont(customFont); // Ustawienie czcionki dla przycisku
        btnDodajSeans.setBackground(new Color(144, 238, 144));
        btnDodajSeans.addActionListener(e -> {
            String tytul = (String) filmComboBox.getSelectedItem();
            Film film = dbManager.znajdzFilm(tytul);
            if (film == null) {
                JOptionPane.showMessageDialog(frame, "Film nie znaleziony.");
                return;
            }
            String sala = (String) salaComboBox.getSelectedItem();
            int pojemnoscSali = Integer.parseInt(sala.split("\\(")[1].split(" ")[0]);
            String godzina = (String) godzinaComboBox.getSelectedItem();

            Screening screening = new Screening(film, godzina, new Hall(sala, pojemnoscSali));
            dbManager.dodajSeansDoBazy(screening);

            JOptionPane.showMessageDialog(frame, "Seans dodany!");

            // Czyszczenie pól po dodaniu seansu
            filmComboBox.setSelectedIndex(0);
            salaComboBox.setSelectedIndex(0);
            godzinaComboBox.setSelectedIndex(0);
        });
        panel.add(btnDodajSeans, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private String[] generateTimeSlots() {
        String[] timeSlots = new String[45];
        int index = 0;
        for (int hour = 11; hour <= 21; hour++) {
            for (int minute = 0; minute < 60; minute += 15) {
                timeSlots[index++] = String.format("%02d:%02d", hour, minute);
            }
        }
        timeSlots[index] = "22:00"; // Dodanie ostatniego czasu
        return timeSlots;
    }
}
