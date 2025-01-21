package org.example.database;

import org.example.film.Film;
import org.example.screening.Screening;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScreeningDatabaseManager extends DatabaseManager {

    public ScreeningDatabaseManager() {
        inicjalizujBazeDanych();
    }

    public void dodajSeansDoBazy(Screening screening) {
        String sql = "INSERT INTO seanse (film_tytuł, godzina, sala_numer, sala_pojemność) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, screening.getFilm().getTytul());
            pstmt.setString(2, screening.getGodzina());
            pstmt.setString(3, screening.getSala().getNumer());
            pstmt.setInt(4, screening.getSala().getPojemnosc());

            pstmt.executeUpdate();
            System.out.println("Seans został zapisany do bazy danych.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> pobierzTytulyFilmowZBazy() {
        List<String> tytulyFilmow = new ArrayList<>();
        String sql = "SELECT tytuł FROM filmy";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                tytulyFilmow.add(rs.getString("tytuł"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tytulyFilmow;
    }

    public Film znajdzFilm(String tytul) {
        String sql = "SELECT * FROM filmy WHERE tytuł = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tytul);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Film(
                            rs.getString("tytuł"),
                            rs.getString("gatunek"),
                            rs.getInt("czas_trwania"),
                            rs.getString("reżyser"),
                            rs.getDouble("ocena")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
