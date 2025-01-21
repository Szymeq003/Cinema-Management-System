package org.example.cinema;

import org.example.database.DatabaseManager;
import org.example.film.Film;

import org.example.screening.Screening;
import org.example.ticket.Ticket;
import org.example.member.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cinema {
    private List<Film> filmy;
    private List<Screening> seanse;
    private List<Ticket> bilety;
    private List<User> uzytkownicy;
    private DatabaseManager dbManager;

    public Cinema(DatabaseManager dbManager) {
        this.filmy = new ArrayList<>();
        this.seanse = new ArrayList<>();
        this.bilety = new ArrayList<>();
        this.uzytkownicy = new ArrayList<>();
        this.dbManager = dbManager;
    }

    public Screening znajdzSeans(String tytul, String godzina) {
        return seanse.stream()
                .filter(s -> s.getFilm().getTytul().equals(tytul) && s.getGodzina().equals(godzina))
                .findFirst()
                .orElse(null);
    }

    public void usunSeans(Screening screening) {
        seanse = seanse.stream()
                .filter(s -> !s.equals(screening))
                .collect(Collectors.toList());
    }

    public int liczbaSprzedanychBiletów() {
        String sql = "SELECT COUNT(*) FROM bilety WHERE sprzedany = TRUE";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double dochodZeSprzedazy() {
        String sql = "SELECT SUM(cena) FROM bilety WHERE sprzedany = TRUE";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public List<Film> getFilmy() {
        return filmy;
    }

    public List<Screening> getSeanse() {
        return seanse;
    }

    public List<User> getUzytkownicy() {
        return uzytkownicy;
    }

    public List<Ticket> getBilety() {
        return bilety;
    }


    public Film znajdzFilm(String tytul) {
        return filmy.stream().filter(f -> f.getTytul().equals(tytul)).findFirst().orElse(null);
    }

    public User znajdzUzytkownika(String email) {
        return uzytkownicy.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
    }

    public DatabaseManager getDatabaseManager() {
        return dbManager;
    }

}
