package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:sqlite:kinobaza.db";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    public void inicjalizujBazeDanych() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            // Tworzenie tabeli użytkownicy
            String createUzytkownicyTable = "CREATE TABLE IF NOT EXISTS użytkownicy (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "imię TEXT, " +
                    "nazwisko TEXT, " +
                    "email TEXT, " +
                    "numer_telefonu TEXT)";
            stmt.execute(createUzytkownicyTable);

            // Tworzenie tabeli filmy
            String createFilmyTable = "CREATE TABLE IF NOT EXISTS filmy (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "tytuł TEXT, " +
                    "gatunek TEXT, " +
                    "czas_trwania INTEGER, " +
                    "reżyser TEXT, " +
                    "ocena REAL)";
            stmt.execute(createFilmyTable);

            // Tworzenie tabeli seanse
            String createSeanseTable = "CREATE TABLE IF NOT EXISTS seanse (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "film_tytuł TEXT, " +
                    "godzina TEXT, " +
                    "sala_numer TEXT, " +
                    "sala_pojemność INTEGER)";
            stmt.execute(createSeanseTable);

            // Tworzenie tabeli bilety
            String createBiletyTable = "CREATE TABLE IF NOT EXISTS bilety (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "film_tytuł TEXT, " +
                    "miejsce TEXT, " +
                    "cena REAL, " +
                    "rodzaj_biletu TEXT, " +
                    "sprzedany BOOLEAN)";
            stmt.execute(createBiletyTable);

            System.out.println("Baza danych została zainicjalizowana.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
