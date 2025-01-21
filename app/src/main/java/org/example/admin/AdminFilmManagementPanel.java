package org.example.admin;

import org.example.database.FilmDatabaseManager;
import org.example.film.Film;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class AdminFilmManagementPanel {
    private FilmDatabaseManager filmDatabaseManager;

    public AdminFilmManagementPanel(FilmDatabaseManager filmDatabaseManager) {
        this.filmDatabaseManager = filmDatabaseManager;
        createFilmManagementFrame();
    }

    private void createFilmManagementFrame() {
        JFrame frame = new JFrame("Wyświetl Filmy");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextArea filmTextArea = new JTextArea(20, 50);
        filmTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(filmTextArea);

        StringBuilder message = new StringBuilder("Lista filmów:\n");
        List<Film> filmy = filmDatabaseManager.pobierzFilmyZBazy(); // Pobieranie filmów z bazy danych
        for (Film film : filmy) {
            message.append(" - ").append(film.getTytul()).append(" (").append(film.getGatunek()).append(")\n");
        }
        filmTextArea.setText(message.toString());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
