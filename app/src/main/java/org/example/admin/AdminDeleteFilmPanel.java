package org.example.admin;

import org.example.cinema.Cinema;
import org.example.film.Film;
import org.example.member.Admin;
import org.example.database.FilmDatabaseManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class AdminDeleteFilmPanel {
    private Cinema cinema;
    private Admin admin;
    private FilmDatabaseManager filmDatabaseManager;

    public AdminDeleteFilmPanel(Cinema cinema) {
        this.cinema = cinema;
        this.admin = new Admin(cinema);
        this.filmDatabaseManager = new FilmDatabaseManager(); // Initialize FilmDatabaseManager
        createDeleteFilmFrame();
    }

    private void createDeleteFilmFrame() {
        JFrame frame = new JFrame("Usuń Film");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Pobieranie filmów z bazy danych
        List<Film> filmy = filmDatabaseManager.pobierzFilmyZBazy();

        // Dodawanie rozwijanego menu z dostępnymi tytułami filmów
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Wybierz film:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> filmComboBox = new JComboBox<>();
        filmComboBox.addItem("---");
        for (Film film : filmy) {
            filmComboBox.addItem(film.getTytul() + " (" + film.getGatunek() + ")");
        }
        filmComboBox.setPreferredSize(new Dimension(300, 25));
        panel.add(filmComboBox, gbc);

        // Dodawanie przycisku "Usuń film"
        JButton btnUsunFilm = new JButton("Usuń film");
        btnUsunFilm.addActionListener(e -> {
            String selectedFilm = (String) filmComboBox.getSelectedItem();
            if (selectedFilm != null) {
                String tytul = selectedFilm.split(" \\(")[0]; // Extracting title from the selection
                Film film = cinema.znajdzFilm(tytul);
                if (film != null) {
                    cinema.getFilmy().remove(film);
                    admin.usunFilm(tytul);
                    JOptionPane.showMessageDialog(frame, "Film został usunięty.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Film nie znaleziony.");
                }
            }
        });

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(btnUsunFilm, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
