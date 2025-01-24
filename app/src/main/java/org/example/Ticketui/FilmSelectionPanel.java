package org.example.Ticketui;

import org.example.database.TicketDataBaseManager;
import org.example.film.Film;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FilmSelectionPanel extends JPanel {
    private JComboBox<String> filmComboBox;

    public FilmSelectionPanel(TicketDataBaseManager dbManager) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Tytuł filmu:"), gbc);

        gbc.gridx = 1;
        List<Film> filmy = dbManager.pobierzFilmyZBazy();
        filmComboBox = new JComboBox<>(filmy.stream().map(Film::getTytul).toArray(String[]::new));
        filmComboBox.setPreferredSize(new Dimension(200, 25));
        add(filmComboBox, gbc);
    }

    public String getSelectedFilm() {
        return (String) filmComboBox.getSelectedItem();
    }
}
