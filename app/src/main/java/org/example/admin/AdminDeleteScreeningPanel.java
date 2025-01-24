package org.example.admin;

import org.example.cinema.Cinema;
import org.example.screening.Screening;
import org.example.member.Admin;
import org.example.database.ScreeningDatabaseManager;
import org.example.screening.Hall;
import org.example.film.Film;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDeleteScreeningPanel {
    private Cinema cinema;
    private Admin admin;
    private ScreeningDatabaseManager dbManager;

    public AdminDeleteScreeningPanel(Cinema cinema) {
        this.cinema = cinema;
        this.admin = new Admin(cinema);
        this.dbManager = new ScreeningDatabaseManager();
        createDeleteScreeningFrame();
    }

    private void createDeleteScreeningFrame() {
        JFrame frame = new JFrame("Usuń Seans");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 200);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        List<Screening> seanse = pobierzSeanseZBazy();

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Wybierz seans:"), gbc);
        gbc.gridx = 1;
        JComboBox<String> screeningComboBox = new JComboBox<>();
        screeningComboBox.addItem("---");
        for (Screening screening : seanse) {
            screeningComboBox.addItem(screening.toString());
        }
        screeningComboBox.setPreferredSize(new Dimension(400, 25));
        panel.add(screeningComboBox, gbc);

        JButton btnUsunSeans = new JButton("Usuń seans");
        btnUsunSeans.addActionListener(e -> {
            String selectedScreening = (String) screeningComboBox.getSelectedItem();
            if (selectedScreening != null && !selectedScreening.equals("---")) {
                Screening screening = seanse.stream().filter(s -> s.toString().equals(selectedScreening)).findFirst().orElse(null);
                if (screening != null) {
                    cinema.getSeanse().remove(screening);
                    admin.usunSeans(screening);
                    JOptionPane.showMessageDialog(frame, "Seans został usunięty.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Proszę wybrać seans.");
            }
        });

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(btnUsunSeans, gbc);

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
                String tytul = rs.getString("film_tytuł");
                String godzina = rs.getString("godzina");
                String salaNumer = rs.getString("sala_numer");
                int salaPojemnosc = rs.getInt("sala_pojemność");
                Film film = dbManager.znajdzFilm(tytul);
                Hall hall = new Hall(salaNumer, salaPojemnosc);
                Screening screening = new Screening(film, godzina, hall);
                seanse.add(screening);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seanse;
    }
}
