package org.example.database;

import org.example.film.Film;
import org.example.screening.Screening;
import org.example.screening.Hall;
import org.example.ticket.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDataBaseManager extends DatabaseManager {

    public TicketDataBaseManager() {
        inicjalizujBazeDanych();
    }

    public void sprzedajBiletDoBazy(Ticket ticket) {
        String sql = "INSERT INTO bilety (film_tytuł, miejsce, cena, rodzaj_biletu, sprzedany) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ticket.getSeans().getFilm().getTytul());
            pstmt.setString(2, ticket.getMiejsce());
            pstmt.setDouble(3, ticket.getCena());
            pstmt.setString(4, ticket.getRodzajBiletu().name());
            pstmt.setBoolean(5, ticket.isSprzedany());

            pstmt.executeUpdate();
            System.out.println("Bilet został zapisany do bazy danych.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Film> pobierzFilmyZBazy() {
        List<Film> filmy = new ArrayList<>();
        String sql = "SELECT * FROM filmy";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Film film = new Film(
                        rs.getString("tytuł"),
                        rs.getString("gatunek"),
                        rs.getInt("czas_trwania"),
                        rs.getString("reżyser"),
                        rs.getDouble("ocena")
                );
                filmy.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filmy;
    }

    public List<Screening> pobierzSeanseDlaFilmu(String tytul) {
        List<Screening> seanse = new ArrayList<>();
        String sql = "SELECT * FROM seanse WHERE film_tytuł = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tytul);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Screening screening = new Screening(
                            new Film(tytul, "", 0, "", 0),  // Tworzymy prosty obiekt filmu
                            rs.getString("godzina"),
                            new Hall(rs.getString("sala_numer"), rs.getInt("sala_pojemność"))
                    );
                    seanse.add(screening);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seanse;
    }
}
