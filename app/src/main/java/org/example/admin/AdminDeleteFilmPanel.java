package org.example.admin;

import org.example.cinema.Cinema;
import org.example.film.Film;
import org.example.member.Admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminDeleteFilmPanel {
    private Cinema cinema;
    private Admin admin;

    public AdminDeleteFilmPanel(Cinema cinema) {
        this.cinema = cinema;
        this.admin = new Admin(cinema);
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

        JTextField tytulField = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Tytuł:"), gbc);
        gbc.gridx = 1;
        panel.add(tytulField, gbc);

        JButton btnUsunFilm = new JButton("Usuń film");
        btnUsunFilm.addActionListener(e -> {
            String tytul = tytulField.getText();
            Film film = cinema.znajdzFilm(tytul);
            if (film != null) {
                cinema.getFilmy().remove(film);
                admin.usunFilm(tytul);
                JOptionPane.showMessageDialog(frame, "Film został usunięty.");
            } else {
                JOptionPane.showMessageDialog(frame, "Film nie znaleziony.");
            }
        });

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(btnUsunFilm, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
