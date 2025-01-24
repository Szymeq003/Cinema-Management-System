package org.example.admin;

import org.example.cinema.Cinema;
import org.example.screening.Screening;
import org.example.film.Film;
import org.example.screening.Hall;
import org.example.database.ScreeningDatabaseManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminScreeningManagementPanel {
    private Cinema cinema;
    private ScreeningDatabaseManager dbManager;

    public AdminScreeningManagementPanel(Cinema cinema) {
        this.cinema = cinema;
        this.dbManager = new ScreeningDatabaseManager();
        createScreeningManagementFrame();
    }

    private void createScreeningManagementFrame() {
        JFrame frame = new JFrame("Wyświetl Seanse");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextArea screeningTextArea = new JTextArea(20, 50);
        screeningTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(screeningTextArea);

        StringBuilder message = new StringBuilder("Lista seansów:\n");
        List<Screening> seanse = pobierzSeanseZBazy();
        for (Screening screening : seanse) {
            Film film = screening.getFilm();
            if (film != null) {
                message.append(" - ").append(film.getTytul())
                        .append(" o godz. ").append(screening.getGodzina())
                        .append(" w sali numer ").append(screening.getSala().getNumer())
                        .append(" (").append(screening.getSala().getPojemnosc()).append(" miejsc)\n");
            } else {
                message.append(" - Film nie znaleziony")
                        .append(" o godz. ").append(screening.getGodzina())
                        .append(" w sali numer ").append(screening.getSala().getNumer())
                        .append(" (").append(screening.getSala().getPojemnosc()).append(" miejsc)\n");
            }
        }
        screeningTextArea.setText(message.toString());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private List<Screening> pobierzSeanseZBazy() {
        List<Screening> seanse = new ArrayList<>();
        String sql = "SELECT * FROM seanse";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String tytuł = rs.getString("film_tytuł");
                String godzina = rs.getString("godzina");
                String salaNumer = rs.getString("sala_numer");
                int salaPojemność = rs.getInt("sala_pojemność");
                Film film = dbManager.znajdzFilm(tytuł);
                Hall hall = new Hall(salaNumer, salaPojemność);
                Screening screening = new Screening(film, godzina, hall);
                seanse.add(screening);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seanse;
    }
}
