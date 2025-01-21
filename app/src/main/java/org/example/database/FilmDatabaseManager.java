package org.example.database;

import org.example.film.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDatabaseManager extends DatabaseManager {

    public void dodajFilmDoBazy(Film film) {
        String sql = "INSERT INTO filmy (tytul, gatunek, czas_trwania, rezyser, ocena) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, film.getTytul());
            pstmt.setString(2, film.getGatunek());
            pstmt.setInt(3, film.getCzasTrwania());
            pstmt.setString(4, film.getRezyser());
            pstmt.setDouble(5, film.getOcena());

            pstmt.executeUpdate();
            System.out.println("Film został zapisany do bazy danych.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Film> pobierzFilmyZBazy() {
        List<Film> filmy = new ArrayList<>();
        String sql = "SELECT tytuł, gatunek, czas_trwania, reżyser, ocena FROM filmy";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String tytul = rs.getString("tytuł");
                String gatunek = rs.getString("gatunek");
                int czasTrwania = rs.getInt("czas_trwania");
                String rezyser = rs.getString("reżyser");
                double ocena = rs.getDouble("ocena");

                Film film = new Film(tytul, gatunek, czasTrwania, rezyser, ocena);
                filmy.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filmy;
    }
}
